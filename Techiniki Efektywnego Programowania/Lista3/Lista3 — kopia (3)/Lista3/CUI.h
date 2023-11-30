#pragma once
#include"CTree.h"

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
