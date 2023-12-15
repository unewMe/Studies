#include"CTree.h"
#include<iostream>
#include <string>
#include"CUI.h"

int main()
{
	//CTree<int> tree;
	//CUI<std::string> cui;
	//cui.start();
	std::string s1 = "\"xaxxax\"";
	std::string s2 = "\"xax\"";
	std::string res = s1 - s2;
	std::cout << res;
	return 0;
}