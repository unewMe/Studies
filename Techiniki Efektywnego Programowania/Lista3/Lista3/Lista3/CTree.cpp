#include"CTree.h"
#include<regex>
#include<iostream>
#include<sstream>


std::unordered_map<std::string, int> CTree::funMap = { {"+",2},{"-",2},{"*",2},{"/",2},{"sin",1},{"cos",1}};
bool ifLetter(const std::string& next);

std::string takeNext(std::string& expression);


CTree::CTree()
{
	root = nullptr;
}

CTree::CTree(const CTree& tree)
{
	root = new CNode(*(tree.root));
	args = tree.args;
	argsValueMap = tree.argsValueMap;
	argsCountMap = tree.argsCountMap;
}

CTree::~CTree()
{
	if (root != nullptr)
		delete root;
}


bool CTree::enter(std::string expression)
{
	std::string next = "";
	while ((next = takeNext(expression)) != "")
	{
		removeUnnesesary(next, true);
		if (next == "")
		{

		}
		else
		{
			root = new CNode(next, whatAmI(next));
			if ((*root).getWhatAmI() == 0)
			{
				args.push_back(next);
				argsValueMap[next] = -1;
				argsCountMap[next]++;
				return checkRestOfExpresion(expression);
			}
			else if ((*root).getWhatAmI() == 1)
			{
				addNextNode(root, expression);
				return checkRestOfExpresion(expression);
			}
			else
			{
				return checkRestOfExpresion(expression);
			}

		}
	}
	return false;
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
		if (argsValueMap[current->getValue()] == 0)
		{
			args.push_back(current->getValue());
			argsValueMap[current->getValue()] = -1;
		}
		argsCountMap[current->getValue()]++;
	}
	while (argsCount > 0)
	{
		std::string next = takeNext(expression);
		removeUnnesesary(next, true);
		CNode* newNode;
		if (next == "")
		{
			newNode = new CNode(CONSTINT, whatAmI(next));
			ifConstUsed = true;
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

bool CTree::checkRestOfExpresion(std::string& expression)
{
	std::string next = "";
	bool state = true;
	while ((next = takeNext(expression)) != "")
	{
		removeUnnesesary(next, true);
		if (next != "")
		{
			state = false;
			zbior.insert(next);
		}
	}
	return state;
}

double CTree::comp(std::string expression)
{


	if (root == nullptr)
	{
		throw std::exception();
	}


	std::vector<std::string> argsValues;
	std::string next = "";
	while ((next = takeNext(expression)) != "")
	{
		std::regex patternNumber("[0-9]+");
		notRemove(next, patternNumber, false);

		if (next != "")
		{
			argsValues.push_back(next);
		}

	}

	if (argsValues.size() != args.size())
	{
		throw std::exception();
	}

	for (int i = 0; i < args.size(); i++)
	{
		argsValueMap[args[i]] = std::stod(argsValues[i]);
	}

	return comp(root);
}

double CTree::comp(const CNode* current)
{
	if (current->getWhatAmI() == 1)
	{
		if (current->getValue() == "+")
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
		return argsValueMap[current->getValue()];
	}
	else
	{
		return std::stod(current->getValue());
	}
}

CTree CTree::join(const CTree& tree) const
{
	if (root != nullptr && tree.root != nullptr)
	{
		return joinHelper(tree);
	}
	else
	{
		throw std::exception();
	}

}

CTree CTree::join(std::string expression) const
{
	CTree tree;
	tree.enter(expression);

	if (root != nullptr && tree.root != nullptr)
	{
		return joinHelper(tree);
	}
	else
	{
		throw std::exception();
	}
}

CTree CTree::joinHelper(const CTree& tree) const
{
	if (root == nullptr || root->getWhatAmI() != 1)
	{
		return tree;
	}


	CTree res;
	res = *this;

	CNode* nodeBefore = getNodeBefore(res.root);
	int index = nodeBefore->getChildrenCount() - 1;
	if (nodeBefore->getChild(index)->getWhatAmI() == 0)
	{
		if (res.argsCountMap[nodeBefore->getChild(index)->getValue()] == 1)
		{
			res.argsCountMap.erase(nodeBefore->getChild(index)->getValue());
			res.argsValueMap.erase(nodeBefore->getChild(index)->getValue());
			res.args.erase(res.args.begin() + res.indexOf(nodeBefore->getChild(index)->getValue()));
		}
		else
		{
			res.argsCountMap[nodeBefore->getChild(index)->getValue()]--;
		}
	}
	delete (nodeBefore->getChild(index));
	nodeBefore->setChild(index, *(tree.root));

	for (int i = 0; i < tree.args.size(); i++)
	{
		res.argsCountMap[tree.args[i]]++;
		res.argsValueMap[tree.args[i]] = -1;
		res.args.push_back(tree.args[i]);
	}

	return res;
}


CNode* CTree::getNodeBefore(CNode* current) const
{
	if (current->getChild(current->getChildrenCount() - 1)->getChildrenCount() <= 0)
	{
		return current;
	}
	else
	{
		return getNodeBefore(current->getChild(current->getChildrenCount() - 1));
	}
}


std::vector<std::string> CTree::getArgs() const
{
	return args;
}

std::string CTree::print() const
{
	if (root == nullptr)
	{
		throw std::exception();
	}
	std::stringstream stringBuffer;
	printChild(root, stringBuffer);
	return stringBuffer.str();
}

void CTree::printChild(const CNode* current, std::stringstream& stringBuffer) const
{
	//std::cout << (*current).getValue();
	stringBuffer << (*current).getValue() << " ";
	int counter = 0;
	while (counter < (*current).getChildrenCount())
	{
		printChild((*current).getChild(counter), stringBuffer);
		counter++;
	}
}

int CTree:: whatAmI(const std::string& next) const
{
	std::regex patternLetter("[a-zA-Z]");//("[a - zA - Z].*[a - zA - Z0 - 9] * ");//("[a - zA - Z] + [a - zA - Z0 - 9] * ");
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

		if (ifLetter(next))
		{
			return 0;
		}
		else
		{
			return -1;
		}

	}

}

int CTree:: getZbiorSize() const
{
	return zbior.size();
}



CTree& CTree::operator=(const CTree& tree)
{
	if (this == &tree)
	{
		return *this;
	}
	else
	{
		delete root;
		root = new CNode(*(tree.root));
		args = tree.args;
		argsValueMap = tree.argsValueMap;
		argsCountMap = tree.argsCountMap;
		return *this;
	}
}

CTree CTree::operator+(const CTree& tree)
{
	return join(tree);
}


bool CTree::checkIfConstUsed() const
{
	return ifConstUsed;
}

void CTree::resetArgsValues()
{
	for (int i = 0; i < args.size(); i++)
	{
		argsValueMap[args[i]] = -1;
	}
}

std::string CTree::unUsedElements() const
{
	std::stringstream res;
	for (std::set<std::string>::iterator iterator = zbior.begin(); iterator != zbior.end(); ++iterator) 
	{
		res << *iterator << " ";
	}
	return res.str();
}

std::string CTree::getArgsValueString()
{
	std::stringstream res;
	for (int i = 0; i < args.size(); i++)
	{
	res << args[i] + " = " + std::to_string(argsValueMap[args[i]]) + "\n";
	}
	return res.str();

}

void CTree::resetTree()
{
	delete root;
	root = nullptr;
	args.clear();
	argsValueMap.clear();
	argsCountMap.clear();
	zbior.clear();
	ifConstUsed= false;
}

bool CTree::ifTreeExists() const
{
	return root != nullptr;
}


void CTree::notRemove(std::string& expression,const std::regex& pattern, bool ifAdd)
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
		else if (ifAdd)
		{
			zbior.insert(toMatch);
		}
	}
	expression = res;
}

void CTree::removeUnnesesary(std::string& expression, bool ifAdd)
{
	int currentSize = expression.size();
	std::regex patternLetter("[a-zA-Z0-9]+");
	std::regex patternNumber("[0-9]+");
	std::regex patternOperator("[+-/*]");
	while (expression != "")
	{
		std::string toMatch(1, expression[0]);
		if (std::regex_match(toMatch, patternLetter))
		{
			notRemove(expression, patternLetter, ifAdd);
			return;
		}
		else if (std::regex_match(toMatch, patternOperator))
		{
			notRemove(expression, patternOperator, ifAdd);
			expression = toMatch;
			return;
		}
		else
		{
			expression.erase(0, 1);
		}
	}

}

int CTree::indexOf(const std::string& arg) const
{
	for (int i = 0; i < args.size(); i++)
	{
		if (args[i] == arg)
		{
			return i;
		}
	}
	return -1;
}


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

bool ifLetter(const std::string& next)
{
	std::regex patternLetter("[a-zA-Z]");
	for (int i = 0; i < next.size(); i++)
	{
		if (std::regex_match(std::string(1, next[i]), patternLetter))
		{
			return true;
		}
	}
	return false;
}