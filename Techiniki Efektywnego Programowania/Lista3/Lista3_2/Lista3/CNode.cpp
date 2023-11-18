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
 
CNode* CNode::getChild(int index) const	
{
	return children[index];
}
int CNode::getChildrenCount() const
{
	return childrenCount;
}

void CNode::setChild(int index, CNode child)
{
	children[index] = new CNode(child);
}

CNode::~CNode()
{
	for (int i = 0; i < children.size(); i++)
	{
		delete children[i];
	}
}

CNode::CNode(const CNode& node)
{

	for (int i = 0; i < node.children.size(); i++)
	{
		children.push_back(new CNode((*node.children[i])));
	}
	childrenCount = node.childrenCount;
	value = node.value;
	whatAmI = node.whatAmI;
}

CNode& CNode::operator=(const CNode& node)
{
	if (this == &node)
	{
		return *this;
	}
	for (int i = 0; i < node.children.size(); i++)
	{
		children.push_back(new CNode((*node.children[i])));
	}
	childrenCount = node.childrenCount;
	value = node.value;
	whatAmI = node.whatAmI;
	return *this;

}

int CNode::getWhatAmI() const
{
	return whatAmI;
}
std::string CNode::getValue() const
{
	return value;
}
