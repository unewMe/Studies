#pragma once
#include"CTree.h"
#include<unordered_set>
#include<string>
#include<sstream>
class CUI
{
private:
	CTree tree;
	void enter(CTree& tree);
	void comp();
	void print() const;
	void join();
	void vars() const;
public:
	void start();
};
