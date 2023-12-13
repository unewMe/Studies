#include"CTree.h"
#include<regex>
#include<iostream>
#include<sstream>
#include"stringOperators.h"

template<typename T>
const std::unordered_map<std::string, int> CTree<T>::funMap = { {"+",2},{"-",2},{"*",2},{"/",2},{"sin",1},{"cos",1}};
const std::unordered_map<std::string, int> CTree<std::string>::funMap = { {"+",2},{"-",2},{"*",2},{"/",2}};

template<typename T>
const std::string CTree<T>::CONSTVALUE = "";
const std::string CTree<double>::CONSTVALUE = "1.0";
const std::string CTree<int>::CONSTVALUE = "1";
const std::string CTree<std::string>::CONSTVALUE = "\"default\"";

template <typename T>
const std::regex CTree<T>::patternValue("^$");
const std::regex CTree<double>::patternValue("([1-9][0-9]*\\.\\d+|0\\.\\d*[1-9])");
const std::regex CTree<int>::patternValue("[1-9][0-9]*");
const std::regex CTree<std::string>::patternValue("^\".*\"$");

bool ifLetter(const std::string& phrase);
bool ifLetterAndNumOnly(const std::string& phrase);

template<typename T>
bool ifInMap(const std::string& value, const std::unordered_map<std::string, T>& map);

template<typename T>
T div(T& first, T& second);


std::string takeNext(std::string& expression);



template<typename T>
CTree<T>::CTree() : root(nullptr), ifConstUsed(false), ifTooManyElements(false){} 

template<typename T>
CTree<T>::CTree(const CTree<T>& tree)
{
	setElements(tree);
}

template<typename T>
CTree<T>::~CTree()
{
	deAllocTree();
}

template<typename T>
CTree<T>& CTree<T>::operator=(const CTree<T>& tree)
{
	if (this == &tree)
	{
		return *this;
	}
	else
	{
		deAllocTree();
		setElements(tree);
		return *this;
	}
}

template<typename T>
void CTree<T>::setElements(const CTree<T>& tree)
{
	root = new CNode(*(tree.root));
	vars = tree.vars;
	varsValueMap = tree.varsValueMap;
	varsCountMap = tree.varsCountMap;
	ifConstUsed = tree.ifConstUsed;
	ifTooManyElements = tree.ifTooManyElements;
	unUsedElements = tree.unUsedElements;
}

template<typename T>
CTree<T> CTree<T>::operator+(const CTree<T>& tree) const
{
	if (root != nullptr && tree.root != nullptr)
	{
		if (root->getChildrenCount() == 0)
		{
			return tree;
		}


		CTree res;
		res = *this;

		CNode* nodeBefore = (*res.root).getNodeBeforeMaxR();
		int index = nodeBefore->getChildrenCount() - 1;
		CNode* nodeToRemove = nodeBefore->getChild(index);

		if (nodeToRemove->getWhatAmI() == NodeType::VARIABLE)
		{
			std::string valueOfNodeToRemove = nodeToRemove->getValue();

			if (res.varsCountMap[valueOfNodeToRemove] == 1)
			{
				res.varsCountMap.erase(valueOfNodeToRemove);
				res.varsValueMap.erase(valueOfNodeToRemove);
				res.vars.erase(res.vars.begin() + res.indexOfVar(valueOfNodeToRemove));
			}
			else
			{
				res.varsCountMap[valueOfNodeToRemove]--;
			}
		}

		nodeBefore->setCopyChild(index, *(tree.root));

		int treeVarsSize = static_cast<int>(tree.vars.size());
		for (int i = 0; i < treeVarsSize; i++)
		{
			res.varsCountMap[tree.vars[i]]++;
			if(!ifInMap<T>(tree.vars[i], res.varsValueMap))
			{
				res.varsValueMap[tree.vars[i]] = -1;
				res.vars.push_back(tree.vars[i]);
			}
		}

		return res;
	}
	else
	{
		throw std::invalid_argument("Tree do not exsist");
	}
}

template<typename T>
bool CTree<T>::enter(std::string expression)
{
	root = addNextNode(expression);
	checkRestOfExpresion(expression);
	return true;
}

template<typename T>
CNode* CTree<T>::addNextNode(std::string& expression)
{
	int argsCount = 0;
	std::string next = takeNext(expression);
	removeUnnesesary(next, true);
	CNode* newNode;
	NodeType newNodeType;
	std::string newNodeValue = "";

	if (next == "")
	{
		newNodeType = NodeType::VALUE;
		newNodeValue = CONSTVALUE;
		newNode = new CNode(CONSTVALUE, newNodeType);
		ifConstUsed = true;
	}
	else
	{
		newNodeType = whatNodeIs(next);
		newNodeValue = next;
		newNode = new CNode(next,newNodeType);
	}

	if (newNodeType == NodeType::OPERATOR)
	{
		argsCount = funMap.at(newNode->getValue());
		newNode->allocChildren(argsCount);
	}
	else if (newNodeType == NodeType::VARIABLE)
	{
		if (!ifInMap<T>(newNodeValue,varsValueMap))
		{
			vars.push_back(newNodeValue);
			varsValueMap[newNodeValue];
		}
		varsCountMap[newNodeValue]++;
	}

	while (argsCount > 0)
	{
		newNode->pushChld(addNextNode(expression));
		argsCount--;
	}

	return newNode;
}

template<typename T>
void CTree<T>::checkRestOfExpresion(std::string& expression)
{
	std::string next = "";

	while ((next = takeNext(expression)) != "")
	{
		removeUnnesesary(next, true);
		if (next != "")
		{
			unUsedElements.insert(next);
			ifTooManyElements= true;
		}
	}
}

template<typename T>
T CTree<T>::comp(std::string variables)
{

	if (root == nullptr)
	{
		throw std::invalid_argument("Tree do not exsist");
	}


	std::vector<std::string> argsValues;
	std::string next = "";
	std::regex patternNumber("[0-9]");
	std::regex patternZero("[0]+");

	while ((next = takeNext(variables)) != "")
	{
		//notRemove(next, patternNumber, false);

		if (std::regex_match(next, patternValue))
		{
			argsValues.push_back(next);
		}

	}

	int varsSize = static_cast<int>(vars.size());

	if (static_cast<int>(argsValues.size()) != varsSize)
	{
		throw std::length_error("Wrong number of vars");
	}

	for (int i = 0; i < varsSize; i++)
	{
		varsValueMap[vars[i]] = compValue(argsValues[i]);
	}

	return comp(root);
}


template<typename T>
T CTree<T>::comp(CNode* current) const
{
	NodeType type = current->getWhatAmI();
	std::string value = current->getValue();

	if (type == NodeType::OPERATOR)
	{
		if (value == "-")
		{
			return comp(current->getChild(0)) - comp(current->getChild(1));
		}
		else if (value == "*")
		{
			return comp(current->getChild(0)) * comp(current->getChild(1));
		}
		else if (value == "/")
		{
			T first = comp(current->getChild(0));
			T second = comp(current->getChild(1));
			return div<T>(first, second);

		}
		else
		{
			return restOfComp(value, current);
		}

	}
	else if (type == NodeType::VARIABLE)
	{
		return varsValueMap.at(value);
	}
	else
	{
		return compValue(value);
	}
}

template<typename T>
T CTree<T> ::restOfComp(std::string& value,CNode* current) const
{
	if (value == "+")
	{
		return comp(current->getChild(0)) + comp(current->getChild(1));
	}
	else if (value == "sin")
	{
		return sin(comp(current->getChild(0)));
	}
	else if (value == "cos")
	{
		return cos(comp(current->getChild(0)));
	}
	else
	{
		throw std::invalid_argument("Operation do not exsist");
	}
}
std::string CTree<std::string>::restOfComp(std::string& value, CNode* current) const
{
	if (value == "+")
	{
		std::string first = comp(current->getChild(0));
		std::string second = comp(current->getChild(1));
		return "\"" + first.substr(1, first.size() - 2) + second.substr(1, second.size() - 2) + "\"";
	}
	else
	{
		throw std::invalid_argument("Operation do not exsist");
	}
	
}

template<typename T>
T div(T& first, T& second)
{
	throw std::invalid_argument("Unknown Type");
}
template<>
int div(int& first, int& second)
{
	if (second == 0)
	{
		throw std::invalid_argument("Division by 0");
	}
	return first / second;
}
template<>
double div(double& first, double& second)
{
	if (second == 0.0)
	{
		throw std::invalid_argument("Division by 0");
	}
	return first / second;
}
template<>
std::string div(std::string& first, std::string& second)
{
	return first / second;
}


template<typename T>
T CTree<T>::compValue(std::string& currentValue) const
{
	throw std::invalid_argument("Unknown Type");
}

int CTree<int>::compValue(std::string& currentValue) const
{
	return std::stoi(currentValue);
}

double CTree<double>::compValue(std::string& currentValue) const
{
	return std::stod(currentValue);
}

std::string CTree<std::string>::compValue(std::string& currentValue) const
{
	return currentValue;
}


template<typename T>
std::vector<std::string> CTree<T>::getVars() const
{
	return vars;
}

template<typename T>
std::string CTree<T>::toString() const
{
	if (root == nullptr)
	{
		throw std::invalid_argument("Tree do not exsist");
	}

	return root->toString();
}


template<typename T>
NodeType CTree<T>:: whatNodeIs(const std::string& value) const
{
	std::unordered_map<std::string, int>::const_iterator it = CTree::funMap.find(value);

	if (it == CTree::funMap.end())
	{
		if (ifLetter(value) && ifLetterAndNumOnly(value))
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


template<typename T>
bool CTree<T>::ifWrongExpression() const
{
	return ifTooManyElements || ifConstUsed;
}

template<typename T>
bool CTree<T>::ifConstUsedInExpression() const
{
	return ifConstUsed;
}

template<typename T>
bool CTree<T>:: ifTooManyElementsInExpression() const
{
	return ifTooManyElements;
}

template<typename T>
void CTree<T>::resetVarsValues()
{
	int varsSize = static_cast<int>(vars.size());
	for (int i = 0; i < varsSize; i++)
	{
		varsValueMap[vars[i]] = -1;
	}
}

template<typename T>
int CTree<T>::getUnUsedElementsSize() const
{
	return static_cast<int>(unUsedElements.size());
}

template<typename T>
void CTree<T>::resetErrorVars()
{
	unUsedElements.clear();
	ifConstUsed = false;
	ifTooManyElements= false;
}

template<typename T>
std::string CTree<T>::getUnUsedElements() const
{
	std::stringstream res;

	for (std::unordered_set<std::string>::iterator iterator = unUsedElements.begin(); iterator != unUsedElements.end(); ++iterator) 
	{
		res << *iterator << " ";
	}

	return res.str();
}

template<typename T>
std::string CTree<T>::getVarsValueString()
{
	std::stringstream res;
	int varsSize = static_cast<int>(vars.size());

	for (int i = 0; i < varsSize; i++)
	{
		res << vars[i] + " = " + std::to_string(varsValueMap[vars[i]]) + "\n";
	}

	return res.str();

}

std::string CTree<std::string>::getVarsValueString()
{
	std::stringstream res;
	int varsSize = static_cast<int>(vars.size());

	for (int i = 0; i < varsSize; i++)
	{
		res << vars[i] + " = " + varsValueMap[vars[i]] + "\n";
	}

	return res.str();

}

template<typename T>
void CTree<T>::resetTree()
{
	deAllocTree();
	root = nullptr;
	vars.clear();
	varsValueMap.clear();
	varsCountMap.clear();
	unUsedElements.clear();
	ifConstUsed= false;
	ifTooManyElements = false;
}

template<typename T>
bool CTree<T>::ifTreeExists() const
{
	return root != nullptr;
}

template<typename T>
void CTree<T>:: deAllocTree()
{
	if (root != nullptr)
	{
		delete root;
	}
}

template<typename T>
void CTree<T>::notRemove(std::string& phrase,const std::regex& pattern, bool ifAdd)
{
	int currentSize = static_cast<int>(phrase.size());
	std::string res = "";

	for (int i = 0; i < currentSize; i++)
	{
		std::string toMatch(1, phrase[i]);
		if (std::regex_match(toMatch, pattern))
		{
			res += toMatch;
		}
		else if (ifAdd)
		{
			unUsedElements.insert(toMatch);
		}
	}

	phrase = res;
}

template<typename T>
void CTree<T>::removeUnnesesary(std::string& phrase, bool ifAdd)
{

	if(std::regex_match(phrase, patternValue))
	{
		return;
	}

	std::regex patternLetterAndNumber("[a-zA-Z0-9]");
	std::regex patternOperator("[+-/*]");
	std::regex noPattern("^$");
	int currentSize = static_cast<int>(phrase.size());
	int index = 0;

	while (index<currentSize)
	{
		std::string toMatch(1, phrase[index]);
		if (std::regex_match(toMatch, patternLetterAndNumber))
		{
			notRemove(phrase, patternLetterAndNumber, ifAdd);
			if (!ifLetter(phrase))
			{
				phrase="";
			}
			return;
		}
		else if (std::regex_match(toMatch, patternOperator))
		{
			phrase.erase(index, 1);
			notRemove(phrase, noPattern, ifAdd);
			phrase = toMatch;
			return;
		}
		else
		{
			unUsedElements.insert(toMatch);
		}
		index++;
	}

	phrase = "";
}

template<typename T>
int CTree<T>::indexOfVar(const std::string& var) const
{
	int varsSize = static_cast<int>(vars.size());
	for (int i = 0; i < varsSize; i++)
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
	int currentSize = static_cast<int>(expression.size());
	int index = 0;
	std::string result = "";
	std::string phrase = "";

	while (index < currentSize && expression[index] == ' ')
	{
		index++;
	}

	while (index < currentSize && expression[index] != ' ')
	{
		result += expression[index];
		index++;
	}

	while (index < currentSize)
	{
		phrase += expression[index++];
	}

	expression = phrase;
	return result;
}

bool ifLetter(const std::string& phrase)
{
	std::regex patternLetter("[a-zA-Z]");
	int phraseSize = static_cast<int>(phrase.size());
	for (int i = 0; i < phraseSize; i++)
	{
		if (std::regex_match(std::string(1, phrase[i]), patternLetter))
		{
			return true;
		}
	}

	return false;
}

bool ifLetterAndNumOnly(const std::string& phrase)
{
	std::regex patternLetterAndNumbers("[a-zA-Z0-9]");
	int phraseSize = static_cast<int>(phrase.size());
	for (int i = 0; i < phraseSize; i++)
	{
		if (!std::regex_match(std::string(1, phrase[i]), patternLetterAndNumbers))
		{
			return false;
		}
	}

	return true;
}

template<typename T>
bool ifInMap(const std::string& value, const std::unordered_map<std::string, T>& map) {
    return map.find(value) != map.end();
}
