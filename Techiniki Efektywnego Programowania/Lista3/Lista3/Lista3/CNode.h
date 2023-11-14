#pragma once
#include"CNode.h"
#include<iostream>
#include<vector>

class CNode
{
private:
	std::vector<CNode*> children;
	std::string value;
	std::string* valuePointer;
	int childrenCount;
	int whatAmI;
public:
	CNode();
	CNode(std::string value);
	CNode(std::string value,int whatAmI);
	CNode(const CNode& node);
	CNode* getChild(int index);
	std::string getValue();
	int getWhatAmI();
	void pushChld(CNode* child);
	void setChild(int index, CNode* child);
	void setValue(std::string value);
	void setValuePointer(std::string* valuePointer);
	std::string getValuePointer();
	void setWhatAmI(int whatAmI);
	void incrementChildrenCount();
	int getChildrenCount();
	std::string toString();

	CNode& operator=(const CNode& node);
	

};

