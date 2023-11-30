#include"CNode.h"
#include<string>

CNode::CNode(const std::string& value, const int whatAmI)
{
	this->value = value;
	this->whatAmI = whatAmI;
	childrenCount = 0;
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

CNode::~CNode()
{
	for (int i = 0; i < children.size(); i++)
	{
		delete children[i];
	}
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

void CNode::setChild(const int index,const CNode& child)
{
	children[index] = new CNode(child);
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

void CNode::printChild(std::stringstream& stringBuffer)
{
	stringBuffer << value << " ";
	int counter = 0;
	while (counter < childrenCount)
	{
		(*children[counter]).printChild(stringBuffer);
		counter++;
	}
}

CNode* CNode::getNodeBefore() const
{
	if (this->getChild(this->getChildrenCount() - 1)->getChildrenCount() <= 0)
	{
		return const_cast<CNode*>(this);
	}
	else
	{
		return this->getChild(this->getChildrenCount() - 1)->getNodeBefore();
	}
}
