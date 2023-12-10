#include"CTree.h"
#include<regex>
#include<iostream>
#include<sstream>

template<typename T>
std::unordered_map<std::string, int> CTree<T>::funMap = { {"+",2},{"-",2},{"*",2},{"/",2},{"sin",1},{"cos",1}};
bool ifLetter(const std::string& next);

std::string takeNext(std::string& expression);

template<typename T>
CTree<T>::CTree()
{
	root = nullptr;
	ifConstUsed = false;
}


template<typename T>
CTree<T>::CTree(const CTree& tree)
{
	setElements(tree);
}

template<typename T>
CTree<T>::~CTree()
{
	if (root != nullptr)
		delete root;
}

template<typename T>
bool CTree<T>::enter(std::string expression)
{
	std::string next = "";
	while ((next = takeNext(expression)) != "")
	{
		removeUnnesesary(next, true);
		if (next != "")
		{
			root = new CNode(next, whatAmI(next));
			if ((*root).getWhatAmI() == 0)
			{
				vars.push_back(next);
				varsValueMap[next] = -1;
				varsCountMap[next]++;		
			}
			else if ((*root).getWhatAmI() == 1)
			{
				addNextNode(root, expression);
			}
			checkRestOfExpresion(expression);
		}
		return true;
	}
	return false;
}

template<typename T>
void CTree<T>::addNextNode(CNode<T>* current, std::string& expression)
{
	int argsCount = 0;
	if (current->getWhatAmI() == 1)
	{
		argsCount = funMap[current->getValue()];
	}
	else if (current->getWhatAmI() == 0)
	{
		if (varsValueMap[current->getValue()] == 0)
		{
			vars.push_back(current->getValue());
			varsValueMap[current->getValue()] = -1;
		}
		varsCountMap[current->getValue()]++;
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

template<typename T>
bool CTree<T>::checkRestOfExpresion(std::string& expression)
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

template<typename T>
double CTree<T>::comp(std::string variables)
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
		throw std::length_error("Bad number of vars");
	}

	for (int i = 0; i < vars.size(); i++)
	{
		varsValueMap[vars[i]] = std::stod(argsValues[i]);
	}

	return comp(root);
}

template<typename T>
double CTree<T>::comp(const CNode<T>* current)
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
			throw std::invalid_argument("Operation do not exsist");
		}

	}
	else if (current->getWhatAmI() == 0)
	{
		return varsValueMap[current->getValue()];
	}
	else
	{
		return std::stod(current->getValue());
	}
}

template<typename T>
CTree<T> CTree<T>::join(const CTree<T>& tree) const
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

template<typename T>
CTree<T> CTree<T>::join(std::string expression) const
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

template<typename T>
CTree<T> CTree<T>::joinHelper(const CTree<T>& tree) const
{
	if (root == nullptr || root->getWhatAmI() != 1)
	{
		return tree;
	}


	CTree res;
	res = *this;

	CNode* nodeBefore = (*res.root).getNodeBefore();
	int index = nodeBefore->getChildrenCount() - 1;
	if (nodeBefore->getChild(index)->getWhatAmI() == 0)
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
	delete (nodeBefore->getChild(index));
	nodeBefore->setChild(index, *(tree.root));

	for (int i = 0; i < tree.vars.size(); i++)
	{
		res.varsCountMap[tree.vars[i]]++;
		res.varsValueMap[tree.vars[i]] = -1;
		res.vars.push_back(tree.vars[i]);
	}

	return res;
}


template<typename T>
std::vector<std::string> CTree<T>::getVars() const
{
	return vars;
}

template<typename T>
std::string CTree<T>::print() const
{
	if (root == nullptr)
	{
		throw std::invalid_argument("Tree do not exsist");
	}
	std::stringstream stringBuffer;
	(*root).printChild(stringBuffer);
	return stringBuffer.str();
}


template<typename T>
int CTree<T>:: whatAmI(const std::string& value) const
{
	try
	{
		std::unordered_map<std::string, int>::iterator it = CTree::funMap.find(value);
		if (it == CTree::funMap.end())
		{
			throw std::length_error("");
		}
		else
		{
			return 1;
		}
	}
	catch (const std::length_error&)
	{

		if (ifLetter(value))
		{
			return 0;
		}
		else
		{
			return -1;
		}

	}

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
		delete root;
		setElements(tree);
		//root = new CNode(*(tree.root));
		//vars = tree.vars;
		//varsValueMap = tree.varsValueMap;
		//varsCountMap = tree.varsCountMap;
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
}

template<typename T>
CTree<T> CTree<T>::operator+(const CTree<T>& tree)
{
	return join(tree);
}

template<typename T>
bool CTree<T>::checkIfConstUsed() const
{
	return ifConstUsed;
}

template<typename T>
void CTree<T>::resetVarsValues()
{
	for (int i = 0; i < vars.size(); i++)
	{
		varsValueMap[vars[i]] = -1;
	}
}

template<typename T>
int CTree<T>::getUnUsedElementsSize() const
{
	return unUsedElements.size();
}


template<typename T>
void CTree<T>::resetUnUsedElements()
{
	unUsedElements.clear();
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
	for (int i = 0; i < vars.size(); i++)
	{
	res << vars[i] + " = " + std::to_string(varsValueMap[vars[i]]) + "\n";
	}
	return res.str();

}


template<typename T>
void CTree<T>::resetTree()
{
	delete root;
	root = nullptr;
	vars.clear();
	varsValueMap.clear();
	varsCountMap.clear();
	unUsedElements.clear();
	ifConstUsed= false;
}


template<typename T>
bool CTree<T>::ifTreeExists() const
{
	return root != nullptr;
}


template<typename T>
void CTree<T>::notRemove(std::string& expression,const std::regex& pattern, bool ifAdd)
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


template<typename T>
void CTree<T>::removeUnnesesary(std::string& expression, bool ifAdd)
{
	std::regex patternLetter("[a-zA-Z0-9]+");
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


template<typename T>
int CTree<T>::indexOfVar(const std::string& var) const
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


template<typename T>
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


template<typename T>
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