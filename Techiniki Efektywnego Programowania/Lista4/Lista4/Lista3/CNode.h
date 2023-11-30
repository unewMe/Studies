#pragma once
#include"CNode.h"
#include<iostream>
#include<vector>
#include<sstream>

template<typename T>
class CNode
{
private:
	std::vector<CNode*> children;
	T value;
	int childrenCount;
	int whatAmI;
public:
	CNode();
	CNode(const T& value,const int whatAmI);
	CNode(const CNode<T>& node);
	~CNode();
	CNode* getChild(const int index) const;
	T getValue() const;

	CNode* getNodeBefore() const;
	void printChild(std::stringstream& stringBuffer);

	int getWhatAmI()const ;
	void pushChld(CNode<T>* child);
	void setChild(const int index, const CNode<T>& child);
	void setValue(const T& value);
	void setWhatAmI(const int whatAmI);
	void incrementChildrenCount();
	int getChildrenCount() const;
	std::string toString() const;

	CNode& operator=(const CNode<T>& node);
	

};

