#include"CTree.h"
#include<regex>
#include<iostream>


std::unordered_map<std::string, int> CTree::funMap = { {"+",2},{"-",2},{"*",2},{"/",2},{"sin",1}};
std::string takeNext(std::string& expression)
{
	int currentSize = expression.size();
	std::string result = "";
	while (currentSize>0 && expression[0] == ' ')
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

int CTree:: whatAmI(std::string next)
{
	std::regex patternLetter("[a-zA-Z]+");
	std::regex patternNumber("[0-9]+");
	std::regex patternOperator("[+-/*]+");
	try
	{
		funMap[next];
		return 1;
	}
	catch (const std::exception&)
	{
		//if (std::regex_match(next, patternOperator))
		//{
		//	return 1;
		//}
		//else 
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
		else
		{
			return true;
		}
	}
	addNextNode(root, expression);
}

void CTree::addNextNode(CNode* current, std::string expression)
{
	int argsCount = 0;
	if (current->getWhatAmI() == 1)
	{
		argsCount = funMap[current->getValue()];
	}
	while (argsCount > 0)
	{
		std::string next = takeNext(expression);
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