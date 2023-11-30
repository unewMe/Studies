#include"CTree.h"
#include<regex>
#include<iostream>
#include<sstream>


const std::unordered_map<std::string, int> CTree::funMap = { {"+",2},{"-",2},{"*",2},{"/",2},{"sin",1},{"cos",1}};
bool ifLetter(const std::string& next);
std::string takeNext(std::string& expression);


CTree::CTree()
{
	root = nullptr;
	ifConstUsed = false;
}

CTree::CTree(const CTree& tree)
{
	setElements(tree);
}

CTree::~CTree()
{
	if (root != nullptr)
	{
		delete root;
	}	
}


bool CTree::enter(std::string expression)
{
	root = addNextNode(expression);
	return true;
}

CNode* CTree::addNextNode(std::string& expression)
{
	int argsCount = 0;
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
	}
	if (newNode->getWhatAmI() == NodeType::OPERATOR)
	{
		argsCount = funMap.at(newNode->getValue());
		newNode->allocChildren(argsCount);
	}
	else if (newNode->getWhatAmI() == NodeType::VARIABLE)
	{
		if (varsValueMap[newNode->getValue()] == 0)
		{
			vars.push_back(newNode->getValue());
			varsValueMap[newNode->getValue()] = -1;
		}
		varsCountMap[newNode->getValue()]++;
	}
	while (argsCount > 0)
	{
		newNode->pushChld(addNextNode(expression));
		argsCount--;
	}
	return newNode;
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
			unUsedElements.insert(next);
		}
	}
	return state;
}

double CTree::comp(std::string variables)
{

	if (root == nullptr)
	{
		throw std::invalid_argument("Tree do not exsist");
	}


	std::vector<std::string> argsValues;
	std::string next = "";
	std::regex patternNumber("[0-9]+");

	while ((next = takeNext(variables)) != "")
	{
		notRemove(next, patternNumber, false);
		if (next != "")
		{
			argsValues.push_back(next);
		}

	}

	if (argsValues.size() != vars.size())
	{
		throw std::length_error("Wrong number of vars");
	}

	for (int i = 0; i < vars.size(); i++)
	{
		varsValueMap[vars[i]] = std::stod(argsValues[i]);
	}

	return comp(root);
}

double CTree::comp(const CNode* current)
{
	if (current->getWhatAmI() == NodeType::OPERATOR)
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
			throw std::invalid_argument("Operation do not exsist");
		}

	}
	else if (current->getWhatAmI() == NodeType::VARIABLE)
	{
		return varsValueMap[current->getValue()];
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
		throw std::invalid_argument("Tree do not exsist");
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
		throw std::invalid_argument("Tree do not exsist");
	}
}

CTree CTree::joinHelper(const CTree& tree) const
{
	if (root == nullptr || root->getWhatAmI() != NodeType::OPERATOR)
	{
		return tree;
	}


	CTree res;
	res = *this;

	CNode* nodeBefore = (*res.root).getNodeBefore();
	int index = nodeBefore->getChildrenCount() - 1;
	if (nodeBefore->getChild(index)->getWhatAmI() == NodeType::VARIABLE)
	{
		if (res.varsCountMap[nodeBefore->getChild(index)->getValue()] == 1)
		{
			res.varsCountMap.erase(nodeBefore->getChild(index)->getValue());
			res.varsValueMap.erase(nodeBefore->getChild(index)->getValue());
			res.vars.erase(res.vars.begin() + res.indexOfVar(nodeBefore->getChild(index)->getValue()));
		}
		else
		{
			res.varsCountMap[nodeBefore->getChild(index)->getValue()]--;
		}
	}
	//delete (nodeBefore->getChild(index)); //mozna do CNode przeniesisc
	nodeBefore->setCopyChild(index, *(tree.root));

	for (int i = 0; i < tree.vars.size(); i++)
	{
		res.varsCountMap[tree.vars[i]]++;
		res.varsValueMap[tree.vars[i]] = -1;
		res.vars.push_back(tree.vars[i]);
	}

	return res;
}


std::vector<std::string> CTree::getVars() const
{
	return vars;
}

std::string CTree::toString() const
{
	if (root == nullptr)
	{
		throw std::invalid_argument("Tree do not exsist");
	}
	return root->toString();
}



NodeType CTree:: whatAmI(const std::string& value) const
{
	
	std::unordered_map<std::string, int>::const_iterator it = CTree::funMap.find(value);
	if (it == CTree::funMap.end())
	{
		if (ifLetter(value))
		{
			return NodeType::VARIABLE;
		}
		else
		{
			return NodeType::VALUE;
		}
	}
	else
	{
		return NodeType::OPERATOR;
	}
}


CTree& CTree::operator=(const CTree& tree)
{
	if (this == &tree)
	{
		return *this;
	}
	else
	{
		if (root != nullptr)
		{
			delete root;
		}
		setElements(tree);
		//root = new CNode(*(tree.root));
		//vars = tree.vars;
		//varsValueMap = tree.varsValueMap;
		//varsCountMap = tree.varsCountMap;
		return *this;
	}
}

void CTree::setElements(const CTree& tree)
{
	root = new CNode(*(tree.root));
	vars = tree.vars;
	varsValueMap = tree.varsValueMap;
	varsCountMap = tree.varsCountMap;
}

CTree CTree::operator+(const CTree& tree)
{
	return join(tree);
}


bool CTree::checkIfConstUsed() const
{
	return ifConstUsed;
}

void CTree::resetVarsValues()
{
	for (int i = 0; i < vars.size(); i++)
	{
		varsValueMap[vars[i]] = -1;
	}
}


int CTree::getUnUsedElementsSize() const
{
	return unUsedElements.size();
}

void CTree::resetUnUsedElements()
{
	unUsedElements.clear();
}

std::string CTree::getUnUsedElements() const
{
	std::stringstream res;
	for (std::unordered_set<std::string>::iterator iterator = unUsedElements.begin(); iterator != unUsedElements.end(); ++iterator) 
	{
		res << *iterator << " ";
	}
	return res.str();
}

std::string CTree::getVarsValueString()
{
	std::stringstream res;
	for (int i = 0; i < vars.size(); i++)
	{
	res << vars[i] + " = " + std::to_string(varsValueMap[vars[i]]) + "\n";
	}
	return res.str();

}

void CTree::resetTree()
{
	delete root;
	root = nullptr;
	vars.clear();
	varsValueMap.clear();
	varsCountMap.clear();
	unUsedElements.clear();
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
			unUsedElements.insert(toMatch);
		}
	}
	expression = res;
}

void CTree::removeUnnesesary(std::string& expression, bool ifAdd)
{
	std::regex patternLetterAndNumber("[a-zA-Z0-9]+");
	std::regex patternOperator("[+-/*]");
	while (expression != "")
	{
		std::string toMatch(1, expression[0]);
		if (std::regex_match(toMatch, patternLetterAndNumber))
		{
			notRemove(expression, patternLetterAndNumber, ifAdd);
			return;
		}
		else if (std::regex_match(toMatch, patternOperator))
		{
			notRemove(expression, patternOperator, ifAdd);
			for (int i = 1; i < expression.size(); i++)
			{
				unUsedElements.insert(std::string(1, expression[i]));
			}
			expression = toMatch;
			return;
		}
		else
		{
			unUsedElements.insert(toMatch);
			expression.erase(0, 1);
		}
	}

}

int CTree::indexOfVar(const std::string& var) const
{
	for (int i = 0; i < vars.size(); i++)
	{
		if (vars[i] == var)
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

bool ifLetter(const std::string& value)
{

	std::regex patternLetter("[a-zA-Z]");
	for (int i = 0; i < value.size(); i++)
	{
		if (std::regex_match(std::string(1, value[i]), patternLetter))
		{
			return true;
		}
	}
	return false;
}