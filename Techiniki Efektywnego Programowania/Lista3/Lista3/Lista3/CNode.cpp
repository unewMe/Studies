#include"CNode.h"


CNode::CNode(std::string value, int whatAmI)
{
	this->value = value;
	this->whatAmI = whatAmI;
	childrenCount = 0;
}

void CNode::incrementChildrenCount()
{
	childrenCount++;
}

void CNode::pushChld(CNode* child)
{
	children.push_back(child);
}

CNode* CNode::getChild(int index)
{
	return children[index];
}
int CNode::getChildrenCount()
{
	return childrenCount;
}

int CNode::getWhatAmI()
{
	return whatAmI;
}
std::string CNode::getValue()
{
	return value;
}