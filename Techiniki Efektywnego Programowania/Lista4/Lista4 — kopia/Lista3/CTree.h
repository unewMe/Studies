#pragma once
#include"CNode.h"
#include<iostream>
#include<vector>
#include <unordered_map>
#include <unordered_set>
#include<regex>
//#define CONSTVALUE "1"

template<class T>
class CTree
{
private:
	CNode* root;
	std::vector<std::string> vars;
	std::unordered_map<std::string, T> varsValueMap;
	std::unordered_map<std::string, int> varsCountMap;
	std::unordered_set<std::string> unUsedElements;
	static const std::unordered_map<std::string, int> funMap;
	static const std::regex patternValue;
	static const std::string CONSTVALUE;
	bool ifTooManyElements;
	bool ifConstUsed;

	void checkRestOfExpresion(std::string& expression);
	void removeUnnesesary(std::string& phrase, bool ifAdd);
	void notRemove(std::string& phrase,const std::regex& pattern, bool ifAdd);

	NodeType whatNodeIs(const std::string& value) const;

	int indexOfVar(const std::string& variable) const;
	
	T comp(CNode* current) const;
	T compValue(std::string& currentValue) const;
	T restOfComp(std::string& value, CNode* current) const;

	CNode* addNextNode(std::string& expression);

	void setElements(const CTree<T>& current);
	void deAllocTree();

	//void whichToRemove(std::string& expression, bool ifAdd);

	
public:
	CTree();
	~CTree();
	CTree(const CTree<T> &tree);


	bool enter(std::string expression);

	T comp(std::string variables);

	std::vector<std::string> getVars() const;
	std::string toString() const;


	void resetVarsValues();
	void resetTree();
	void resetErrorVars();

	std::string getUnUsedElements() const;
	std::string getVarsValueString();

	bool ifTreeExists() const;
	bool ifConstUsedInExpression() const;
	bool ifTooManyElementsInExpression() const;
	bool ifWrongExpression() const;

	int getUnUsedElementsSize() const;


	CTree<T>& operator=(const CTree<T>& tree);
	CTree<T> operator+(const CTree<T>& tree) const;

};

#include "CTree.tpp"