#include"CTree.h"
#include<iostream>
#include <string>

int main()
{
	CTree tree;
	std::string expression;
	std::getline(std::cin, expression);
	tree.enter(expression);
	tree.print();
	return 0;
}