#pragma once
#include"CTree.h"

template<typename T>
class CUI
{
private:
	CTree<T> tree;
	void enter(CTree<T>& tree);
	void comp();
	void print() const;
	void join();
	void vars() const;
public:
	void start();
};
