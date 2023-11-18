#pragma once
#include"CTree.h"

class CUI
{
private:
	CTree tree;
	void enter(CTree& tree);
	void comp();
	void print();
	void join();
	void vars();
public:
	void start();
};
