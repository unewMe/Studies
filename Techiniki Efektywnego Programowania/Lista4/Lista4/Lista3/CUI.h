#pragma once
#include"CTree.h"
#include<unordered_set>
#include<string>
#include<sstream>
#include<iostream>
template<class T>
class CUI
{
private:
	CTree<T> tree;
	void enter(CTree<T>& tree);
	void whatNoEntered(CTree<T>& tree);
	void comp();
	void print() const;
	void join();
	void vars() const;
public:
	void start();
};

#include"CUI.tpp"