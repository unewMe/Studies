#pragma once
#include"CNode.h"
#include<iostream>
#include<vector>
#include <unordered_map>
#include <unordered_set>
#include<regex>
#define CONSTINT "1"

template<typename T>
class CTree
{
private:
	CNode* root;
	std::vector<std::string> vars;
	std::unordered_map<std::string, T> varsValueMap;
	std::unordered_map<std::string, T> varsCountMap;
	std::unordered_set<std::string> unUsedElements;
	static std::unordered_map<std::string, int> funMap;

	bool ifConstUsed;
	bool checkRestOfExpresion(std::string& expression);

	void removeUnnesesary(std::string& expression, bool ifAdd);
	void notRemove(std::string& expression,const std::regex& pattern, bool ifAdd);

	int whatAmI(const std::string& value) const;
	int indexOfVar(const std::string& expression) const;
	
	double comp(const CNode<T>* current);
	CTree joinHelper(const CTree<T>& tree) const;

	void addNextNode(CNode<T>* current, std::string& expression);

	void setElements(const CTree<T>& current);
	
public:
	CTree();
	~CTree();
	CTree(const CTree<T>& tree);


	bool enter(std::string expression);

	double comp(std::string variables);

	CTree join(const CTree<T>& tree) const;
	CTree join(std::string expression) const;

	std::vector<std::string> getVars() const;
	std::string print() const;


	void resetVarsValues();
	void resetTree();
	void resetUnUsedElements();
	std::string getUnUsedElements() const;
	std::string getVarsValueString();
	bool ifTreeExists() const;
	bool checkIfConstUsed() const;
	int getUnUsedElementsSize() const;


	CTree& operator=(const CTree<T>& tree);
	CTree operator+(const CTree<T>& tree);

};