#include"CUIN.h"

void CUIN::start()
{
	while (true)
	{
		std::cout << "Enter command int/double/string/exit" << std::endl;
		std::string command;
		std::cin >> command;
		CUI<int> cui;
		if (command == "int")
		{
			cui = CUI<int>;
		}
		else if (command == "double")
		{
			cui = CUI<double>;
		}
		else if (command == "string")
		{
			cui = CTree<std::string>;
		}
		else if (command == "exit")
		{
			return;
		}
		else
		{
			std::cout << "Wrong command! Try Again!"
		}


}