#include"CTree.h"
#include<iostream>
#include <string>
#include"CUI.h"

int main()
{
	CTree<int> tree;
	CTree<int> tree2;
	CTree<int> tree3;
	tree2.enter("+ 1 1");
	tree3.enter("+ 1 a");
	tree = tree2 + tree3;
	tree = std::move(tree2);
	std::cout << tree2.ifTreeExists();
	std::cout<< tree.ifTreeExists();
	std::cout<< tree.comp("");

	//CUI<std::string> cui;
	//cui.start();
	return 0;
}