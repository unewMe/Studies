#pragma once
#include"CTree.h"
#include<unordered_set>
#include<string>
#include<sstream>
#include<iostream>
class CUI
{
private:
	template<typename T>
	void enter(CTree<T>& tree);
	template<typename T>
	void whatNoEntered(CTree<T>& tree);
	template<typename T>
	void comp(CTree<T>& tree);
	template<typename T>
	void print(CTree<T>& tree) const;
	template<typename T>
	void join(CTree<T>& tree);
	template<typename T>
	void vars(CTree<T>& tree) const;
public:
	template<typename T>
	void start();
};

#include"CUI.tpp"