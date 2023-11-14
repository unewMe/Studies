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
	std::cout << std::endl;
	std::cout<<tree.comp("2 4") << std::endl;

	
	std::vector<std::string*> args = tree.getArgs();
	for (int i = 0; i < args.size(); i++)
	{
		std::cout << *args[i] << std::endl;
	}
	return 0;
}