#pragma once
#include"CNode.h"
#include<iostream>
#include<vector>
#include<sstream>

class CNode
{
private:
	std::vector<CNode*> children;
	std::string value;
	int childrenCount;
	int whatAmI;
public:
	CNode();
	CNode(const std::string& value);
	CNode(const std::string& value,const int whatAmI);
	CNode(const CNode& node);
	~CNode();
	CNode* getChild(const int index) const;
	std::string getValue() const;

	CNode* getNodeBefore() const;
	void printChild(std::stringstream& stringBuffer);

	int getWhatAmI()const ;
	void pushChld(CNode* child);
	void setChild(const int index, const CNode& child);
	void setValue(const std::string& value);
	void setWhatAmI(const int whatAmI);
	void incrementChildrenCount();
	int getChildrenCount() const;
	std::string toString() const;

	CNode& operator=(const CNode& node);
	

};

