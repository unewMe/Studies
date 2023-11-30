#include"CUI.h"
#include<string>



void whatNoEntered(CTree& tree);
bool ifTreeNotExsist(const CTree& tree);

void CUI::start()
{
	while (true)
	{
		std::cout << "Enter command enter/comp/vars/join/print/exit" << std::endl;
		std::string command;
		//std::getline(std::cin, command);
		//std::cin.clear();
		std::cin >> command;
		if (command == "enter")
		{
			enter(tree);
		}
		else if (command == "comp")
		{
			comp();
		}
		else if (command == "print")
		{
			print();
		}
		else if (command == "join")
		{
			join();
		}
		else if (command == "vars")
		{
			vars();
		}
		else if (command == "exit")
		{
			return;
		}
		else
		{
			std::cout << "Wrong command! Try again." << std::endl;
		}
	}
}

void CUI::enter(CTree& tree)
{
	tree.resetTree();
	std::cout << "Enter expression" << std::endl;
	std::string expression;
	//std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
	std::cin.ignore();
	std::getline(std::cin, expression);
	if (tree.enter(expression))
	{
		whatNoEntered(tree);
	}
	else
	{
		if (!tree.ifTreeExists())
		{
			std::cout << "Expression not entered" << std::endl;
		}
		else
		{
			whatNoEntered(tree);
		}
	}
}

void whatNoEntered(CTree& tree)
{
	std::cout << "Expression entered: " << tree.print() << std::endl;
	if (tree.getUnUsedElementsSize() != 0)
	{
		std::cout << "But some elements were not used: " << tree.getUnUsedElements() << std::endl;
	}
	
	if (tree.checkIfConstUsed())
	{
		std::cout << "Const(1) was used to fill gaps " << std::endl;
	}
}

void CUI::vars() const
{
	if (!tree.ifTreeExists())
	{
		std::cout << "Expression do not exsist" << std::endl;
		return;
	}

	std::vector<std::string> args = tree.getVars();
	std::cout<<"Variables: ";
	for (int i = 0; i < args.size(); i++)
	{
		std::cout << args[i] +" ";
	}

	std::cout << std::endl;
}
void CUI::comp()
{
	if (!tree.ifTreeExists())
	{
		std::cout << "Expression do not exsist" << std::endl;
		return;
	}
	std::string variables;
	if (tree.getVars().size() != 0)
	{
		std::cout << "Enter value of variables" << std::endl;
		//std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
		std::cin.ignore();
		std::getline(std::cin, variables);
	}

	double res = 0.0;
	try
	{
		res = tree.comp(variables);
		std::cout<<tree.getVarsValueString();
		tree.resetVarsValues();
	}
	catch (std::length_error&)
	{
		std::cout << "Wrong number of arguments" << std::endl;
		return;
	}
	std::cout<<res<<std::endl;
}

void CUI::join()
{
	if (!tree.ifTreeExists())
	{
		std::cout << "Expression do not exsist" << std::endl;
		return;
	}
	CTree tree2;
	enter(tree2);
	if (!tree2.ifTreeExists())
	{
		return;
	}
	tree = tree + tree2;
	std::cout<<"Result: "<<tree.print()<<std::endl;
}

void CUI::print() const
{
	if (!tree.ifTreeExists())
	{
		std::cout << "Expression do not exsist" << std::endl;
		return;
	}
	std::cout << "Expression: " << tree.print() << std::endl;
}

bool ifTreeNotExsist(const CTree& tree)
{
	if (!tree.ifTreeExists())
	{
		std::cout << "Expression do not exsist" << std::endl;
		return true;
	}
	return false;
}