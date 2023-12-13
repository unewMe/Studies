#include"CUI.h"

template<typename T>
bool ifTreeNotExsist(const CTree<T>& tree);

template<typename T>
void CUI<T>::start()
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

template<typename T>
void CUI<T>::enter(CTree<T>& tree)
{
	tree.resetTree();
	std::cout << "Enter expression" << std::endl;
	std::string expression;
	//std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
	std::cin.ignore();
	std::getline(std::cin, expression);
	if (tree.enter(expression))
	{
		std::cout << "Expression entered: " << tree.toString() << std::endl;
		whatNoEntered(tree);
	}
	else
	{
		std::cout << "Expression not entered" << std::endl;
	}
}

template<typename T>
void CUI<T>::whatNoEntered(CTree<T>& tree)
{

	if (tree.ifWrongExpression())
	{
		std::cout << "Wrong expression - it was repaired!" << std::endl;
	}

	if (tree.ifTooManyElementsInExpression())
	{
		std::cout << "Too many elements in expression" << std::endl;
	}

	if (tree.ifConstUsedInExpression())
	{
		std::cout << "Const(1) was used to fill gaps " << std::endl;
	}

	if (tree.getUnUsedElementsSize() != 0)
	{
		std::cout << "Some elements were not used: " << tree.getUnUsedElements() << std::endl;
	}

	tree.resetErrorVars();
}

template<typename T>
void CUI<T>::comp()
{
	if (ifTreeNotExsist(tree))
	{
		return;
	}

	std::string variables;

	if (tree.getVars().size() != 0)
	{
		std::cout << "Enter value of variables" << std::endl;
		//std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
		std::cin.ignore();
	}
	std::getline(std::cin, variables);



	T res;
	try
	{
		res = tree.comp(variables);
		if (tree.getVars().size() != 0)
		{
			std::cout << "Variables values: " << std::endl;
		}
		std::cout<<tree.getVarsValueString();
		tree.resetVarsValues();
	}
	catch (std::exception& a)
	{
		std::cout<<a.what()<<std::endl;
		return;
	}

	std::cout<<"Result: "<<res << std::endl;
}

template<typename T>
void CUI<T>::join()
{
	if (ifTreeNotExsist(tree))
	{
		return;
	}

	CTree<T> tree2;
	enter(tree2);

	if (!tree2.ifTreeExists())
	{
		return;
	}

	tree = tree + tree2;
	std::cout<<"Result: "<<tree.toString()<<std::endl;
}

template<typename T>
void CUI<T>::print() const
{
	if (ifTreeNotExsist(tree))
	{
		return;
	}

	std::cout << "Expression: " << tree.toString() << std::endl;
}

template<typename T>
void CUI<T>::vars() const
{
	if (ifTreeNotExsist(tree))
	{
		return;
	}

	std::vector<std::string> args = tree.getVars();

	if (args.size() == 0)
	{
		std::cout << "No variables" << std::endl;
		return;
	}

	std::cout << "Variables: ";

	for (int i = 0; i < args.size(); i++)
	{
		std::cout << args[i] + " ";
	}

	std::cout << std::endl;
}

template<typename T>
bool ifTreeNotExsist(const CTree<T>& tree)
{
	if (!tree.ifTreeExists())
	{
		std::cout << "Expression do not exsist" << std::endl;
		return true;
	}

	return false;
}