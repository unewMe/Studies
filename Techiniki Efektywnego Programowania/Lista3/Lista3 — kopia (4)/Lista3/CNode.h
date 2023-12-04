#pragma once
#include"CNode.h"
#include<iostream>
#include<vector>
#include<sstream>


enum class NodeType
{
	OPERATOR,
	VALUE,
	VARIABLE,
	UNKNOWN
};

class CNode
{
private:
	CNode** children;
	std::string value;
	int childrenCount;
	int size;
	NodeType whatAmI;
public:
	CNode();
	CNode(const std::string& value);
	CNode(const std::string& value,const NodeType& whatAmI);
	CNode(const CNode& node);
	~CNode();
	CNode* getChild(const int index) const;
	std::string getValue() const;

	CNode* getNodeBefore() const;
	void pushChildrenToBuff(std::stringstream& stringBuffer) const;

	NodeType getWhatAmI()const ;
	void allocChildren(const int count);
	void pushChld(CNode* child);
	void setCopyChild(const int index, const CNode& child);
	void setValue(const std::string& value);
	void setWhatAmI(const int whatAmI);
	int getChildrenCount() const;
	std::string toString() const;

	void deAlloc();
	void setElements(const CNode& node);
	CNode& operator=(const CNode& node);
	

};

