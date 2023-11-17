#include"CTree.h"
#include<iostream>
#include <string>
#include"CNode.h"

int main()
{

	CTree tree;
	std::string expression;
	std::getline(std::cin, expression);
	tree.enter(expression);
	CTree tree2;
	tree2 = tree.join("+ 2 c");
	tree2.print();
	tree.print();
	std::cout << std::endl;
	std::cout<<tree.comp("1") << std::endl;
//	std::cout << tree.comp("2 4") << std::endl;
	CNode node("2", -1);
	node.pushChld(new CNode("4", -1));
	node.incrementChildrenCount();
	std::cout<<CNode(node).getChild(0)->getValue()<<std::endl;
	
	std::vector<std::string> args = tree2.getArgs();
	for (int i = 0; i < args.size(); i++)
	{
		std::cout << args[i] << std::endl;
	}
	return 0;
}