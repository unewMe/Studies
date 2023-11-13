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
	static std::unordered_map<std::string, int> funMap;
public:
	bool enter(std::string expression);
	void print();
	void addNextNode(CNode* current,std::string expression);
	int whatAmI(std::string next);
	void printChild(CNode* current);
};