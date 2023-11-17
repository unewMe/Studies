#pragma once
#include"CNode.h"
#include<iostream>
#include<vector>
#include <unordered_map>
#define CONSTINT "1"


class CTree
{
private:
	CNode* root;
	std::vector<std::string> args;
	std::unordered_map<std::string, int> argsValueMap;
	std::unordered_map<std::string, int> argsCountMap;
	static std::unordered_map<std::string, int> funMap;
public:
	CTree();
	~CTree();
	CTree(const CTree &tree);
	std::vector<std::string> getArgs();
	bool enter(std::string expression);
	void print();
	CTree join(std::string expression);
	CNode* getNodeBefore(CNode* current);
	void addNextNode(CNode* current,std::string& expression);
	double comp(std::string expression);
	double comp(CNode* current);
	int whatAmI(std::string next);
	void printChild(CNode* current);
	bool contains(std::string expression);
	int indexOf(std::string expression);

	CTree& operator=(const CTree& tree);
};