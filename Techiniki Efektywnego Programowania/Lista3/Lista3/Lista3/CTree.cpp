#include"CTree.h"
#include<regex>
#include<iostream>


std::unordered_map<std::string, int> CTree::funMap = { {"+",2},{"-",2},{"*",2},{"/",2},{"sin",1},{"cos",1}};
std::string takeNext(std::string& expression)
{
	int currentSize = expression.size();
	std::string result = "";
	while (currentSize > 0 && expression[0] == ' ')
	{
		expression.erase(0, 1);
		currentSize--;
	}

	while (currentSize > 0 && expression[0] != ' ')
	{
		result += expression[0];
		expression.erase(0, 1);
		currentSize--;
	}

	return result;
}

void notRemove (std::string& expression,std::regex& pattern)
{
	int currentSize = expression.size();
	std::string res = "";
	for (int i = 0; i < currentSize; i++)
	{
		std::string toMatch(1, expression[i]);
		if (std::regex_match(toMatch, pattern))
		{
			res += toMatch;
		}
	}
	expression = res;
}

void removeUnnesesary(std::string& expression)
{
	int currentSize = expression.size();
	std::regex patternLetter("[a-zA-Z]+");
	std::regex patternNumber("[0-9]+");
	std::regex patternOperator("[+-/*]");
	while (expression != "")
	{
		std::string toMatch(1, expression[0]);
		if (std::regex_match(toMatch, patternLetter))
		{
			notRemove(expression, patternLetter);
			return;
		}
		else if (std::regex_match(toMatch, patternNumber))
		{
			notRemove(expression, patternNumber);
			return;
		}
		else if (std :: regex_match(toMatch,patternOperator))
		{
			expression = toMatch;
			return;
		}
		else
		{
			expression.erase(0, 1);
		}
	}



}

int CTree:: whatAmI(std::string next)
{
	std::regex patternLetter("[a-zA-Z]+");
	std::regex patternNumber("[0-9]+");
	int temp;
	try
	{
		std::unordered_map<std::string, int>::iterator it = CTree::funMap.find(next);
		if (it == CTree::funMap.end())
		{
			throw std::exception();
		}
		else
		{
			return 1;
		}
		//temp = funMap[next];
		//return 1;
	}
	catch (const std::exception&)
	{

		if (std::regex_match(next, patternLetter))
		{
			return 0;
		}
		else
		{
			return -1;
		}

	}

}

bool CTree::enter(std::string expression)
{
	std::string next = takeNext(expression);
	removeUnnesesary(next);
	int argsCount = 0;
	if (next == "")
	{
		return false;
	}
	else
	{	
		root = new CNode(next,whatAmI(next));
		if ((*root).getWhatAmI() == 1)
		{
			argsCount = funMap[next];
		}
		else if ((*root).getWhatAmI() == 0)
		{
			args.push_back(new std::string(next));
			(*root).setValuePointer(args[args.size() - 1]);
			return true;
		}
		else
		{
			return true;
		}
	}
	addNextNode(root, expression);
}

void CTree::addNextNode(CNode* current, std::string& expression)
{
	int argsCount = 0;
	if (current->getWhatAmI() == 1)
	{
		argsCount = funMap[current->getValue()];
	}
	else if (current->getWhatAmI() == 0)
	{
		int ifIndexOf = indexOf(current->getValue());
		if (ifIndexOf == -1)
		{
			args.push_back(new std::string(current->getValue()));
			current->setValuePointer(args[args.size() - 1]);
		}
		else
		{
			current->setValuePointer(args[ifIndexOf]);
		}
	}
	while (argsCount > 0)
	{
		std::string next = takeNext(expression);
		removeUnnesesary(next);
		CNode* newNode;
		if (next == "")
		{
			newNode = new CNode(CONSTINT, whatAmI(next));
		}
		else
		{
			newNode = new CNode(next, whatAmI(next));
			addNextNode(newNode, expression);
		
		}
		(*current).pushChld(newNode);
		(*current).incrementChildrenCount();
		argsCount--;
	}
}

void CTree::print()
{
	printChild(root);
	
}

void CTree::printChild(CNode* current)
{
	std::cout << (*current).getValue();
	int counter = 0;
	while (counter < (*current).getChildrenCount())
	{
		printChild((*current).getChild(counter));
		counter++;
	}
}

std::vector<std::string*> CTree::getArgs()
{
	return args;
}

double CTree::comp(std::string expression)
{
	std::vector<std::string> argsValues;
	std::string next = "";
	while((next = takeNext(expression)) != "")
	{
		std::regex patternNumber("[0-9]+");
		notRemove(next,patternNumber);

		if (next != "")
		{
			argsValues.push_back(next);
		}
		
	}

	if(argsValues.size() != args.size())
	{
		throw std::exception();
	}

	for (int i = 0; i < args.size(); i++)
	{
		std::string temp = *args[i];
		*args[i] = argsValues[i];
		argsValues[i] = temp;
	}

	return comp(root);
}

double CTree::comp(CNode* current)
{
	if (current->getWhatAmI() == 1)
	{
		if(current->getValue() == "+")
		{
			return comp(current->getChild(0)) + comp(current->getChild(1));
		}
		else if (current->getValue() == "-")
		{
			return comp(current->getChild(0)) - comp(current->getChild(1));
		}
		else if (current->getValue() == "*")
		{
			return comp(current->getChild(0)) * comp(current->getChild(1));
		}
		else if (current->getValue() == "/")
		{
			return comp(current->getChild(0)) / comp(current->getChild(1));
		}
		else if (current->getValue() == "sin")
		{
			return sin(comp(current->getChild(0)));
		}
		else if (current->getValue() == "cos")
		{
			return cos(comp(current->getChild(0)));
		}
		else
		{
			throw std::exception();
		}
		
	}
	else if (current->getWhatAmI() == 0)
	{
		return std::stod(current->getValuePointer());
	}
	else
	{
		return std::stod(current->getValue());
	}
}

bool CTree::contains(std::string arg)
{
	for (int i = 0; i < args.size(); i++)
	{
		if (*args[i] == arg)
		{
			return true;
		}
	}
	return false;
}
int CTree::indexOf(std::string arg)
{
	for (int i = 0; i < args.size(); i++)
	{
		if (*args[i] == arg)
		{
			return i;
		}
	}
	return -1;
}