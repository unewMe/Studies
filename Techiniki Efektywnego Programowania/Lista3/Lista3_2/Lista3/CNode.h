#pragma once
#include"CNode.h"
#include<iostream>
#include<vector>

class CNode
{
private:
	std::vector<CNode*> children;
	std::string value;
	int childrenCount;
	int whatAmI;
public:
	CNode();
	CNode(std::string value);
	CNode(std::string value,int whatAmI);
	CNode(const CNode& node);
	~CNode();
	CNode* getChild(int index) const;
	std::string getValue() const;


	int getWhatAmI()const ;
	void pushChld(CNode* child);
	void setChild(int index, CNode child);
	void setValue(std::string value);
	void setWhatAmI(int whatAmI);
	void incrementChildrenCount();
	int getChildrenCount() const;
	std::string toString() const;

	CNode& operator=(const CNode& node);
	

};

