#pragma once
#include"CNode.h"
#include<iostream>
#include<vector>
#include <unordered_map>
#include <set>
#include<regex>
#define CONSTINT "1"


class CTree
{
private:
	CNode* root;
	std::vector<std::string> args;
	std::unordered_map<std::string, int> argsValueMap;
	std::unordered_map<std::string, int> argsCountMap;
	std::set<std::string> zbior;
	static std::unordered_map<std::string, int> funMap;

	bool ifConstUsed;
	bool checkRestOfExpresion(std::string& expression);

	void removeUnnesesary(std::string& expression, bool ifAdd);
	void notRemove(std::string& expression,const std::regex& pattern, bool ifAdd);
	void printChild(const CNode* current, std::stringstream& stringBuffer) const;

	int whatAmI(const std::string& value) const;
	int indexOf(const std::string& expression) const;
	
	double comp(const CNode* current);
	CTree joinHelper(const CTree& tree) const;

	CNode* getNodeBefore(CNode* current) const;
	void addNextNode(CNode* current, std::string& expression);
	
public:
	CTree();
	~CTree();
	CTree(const CTree &tree);


	bool enter(std::string expression);

	double comp(std::string expression);

	CTree join(const CTree& tree) const;
	CTree join(std::string expression) const;

	std::vector<std::string> getArgs() const;
	std::string print() const;


	void resetArgsValues();
	void resetTree();
	std::string unUsedElements() const;
	std::string getArgsValueString();
	bool ifTreeExists() const;
	bool checkIfConstUsed() const;
	int getZbiorSize() const;

	CTree& operator=(const CTree& tree);
	CTree operator+(const CTree& tree);

};