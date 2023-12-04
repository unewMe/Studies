#pragma once
#include"CNode.h"
#include<iostream>
#include<vector>
#include <unordered_map>
#include <unordered_set>
#include<regex>
#define CONSTINT "1"


class CTree
{
private:
	CNode* root;
	std::vector<std::string> vars;
	std::unordered_map<std::string, int> varsValueMap;
	std::unordered_map<std::string, int> varsCountMap;
	std::unordered_set<std::string> unUsedElements;
	static const std::unordered_map<std::string, int> funMap;

	bool ifTooManyElements;
	bool ifConstUsed;

	void checkRestOfExpresion(std::string& expression);
	void removeUnnesesary(std::string& phrase, bool ifAdd);
	void notRemove(std::string& phrase,const std::regex& pattern, bool ifAdd);

	NodeType whatNodeIs(const std::string& value) const;

	int indexOfVar(const std::string& variable) const;
	
	double comp(CNode* current) const;

	CNode* addNextNode(std::string& expression);

	void setElements(const CTree& current);
	void deAllocTree();
	
public:
	CTree();
	~CTree();
	CTree(const CTree &tree);


	bool enter(std::string expression);

	double comp(std::string variables);

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


	CTree& operator=(const CTree& tree);
	CTree operator+(const CTree& tree) const;

};