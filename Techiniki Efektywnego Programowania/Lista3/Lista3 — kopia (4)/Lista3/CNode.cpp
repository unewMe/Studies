#include"CNode.h"
#include<string>

CNode::CNode(const std::string& value, const NodeType& whatAmI)
{
	this->value = value;
	this->whatAmI = whatAmI;
	childrenCount = 0;
	size = 0;
	children = nullptr;
}

CNode::CNode(const CNode& node)
{
	setElements(node);
}

CNode::~CNode()
{
	deAlloc();
}

void CNode::deAlloc()
{
	if (children != nullptr)
	{
		for (int i = 0; i < childrenCount; i++)
		{
			delete children[i];
		}
		delete children;
	}
}

void CNode::allocChildren(const int size)
{
	deAlloc();
	children = new CNode * [size];
	this->size = size;
}
void CNode::pushChld(CNode* child)
{
	if (childrenCount < size)
	{
		children[childrenCount++] = child;
	}
	//else
	//{
	//	throw std::out_of_range("Index out of range");
	//}
	
}
 
void CNode::setElements(const CNode& node)
{
	value = node.value;
	whatAmI = node.whatAmI;
	childrenCount = node.childrenCount;
	size = node.size;
	allocChildren(node.childrenCount);
	for (int i = 0; i < node.childrenCount; i++)
	{
		children[i] = (new CNode((*node.children[i])));
	}
}
CNode* CNode::getChild(int index) const	
{
	if (0 <= index && index < childrenCount)
	{
		return children[index];
	}
	return nullptr;
}
int CNode::getChildrenCount() const
{
	return childrenCount;
}

void CNode::setCopyChild(const int index,const CNode& child)
{
	if (0 <= index && index < childrenCount)
	{
		if (children != nullptr && children[index] != nullptr)
		{
			delete children[index];
		}
		children[index] = new CNode(child);
	}
	//else
	//{
	//	throw std::out_of_range("Index out of range");
	//}
}


CNode& CNode::operator=(const CNode& node)
{
	if (this == &node)
	{
		return *this;
	}
	deAlloc();
	setElements(node);
	return *this;

}

NodeType CNode::getWhatAmI() const
{
	return whatAmI;
}
std::string CNode::getValue() const
{
	return value;
}

void CNode::pushChildrenToBuff(std::stringstream& stringBuffer) const
{
	stringBuffer << value << " ";
	int counter = 0;
	while (counter < childrenCount)
	{
		(*children[counter]).pushChildrenToBuff(stringBuffer);
		counter++;
	}
}

CNode* CNode::getNodeBefore() const
{
	//if (this->getChild(this->getChildrenCount() - 1)->getChildrenCount() <= 0)
	//{
	//	return const_cast<CNode*>(this);
	//}
	//else
	//{
	//	return this->getChild(this->getChildrenCount() - 1)->getNodeBefore();
	//}
	if (childrenCount <= 0)
	{
		return nullptr;
	}
	else if (children[childrenCount-1]->childrenCount <= 0)
	{
		return const_cast<CNode*>(this);
	}
	else
	{
		return children[childrenCount - 1]->getNodeBefore();
	}
}

std::string CNode::toString() const
{
	std::stringstream stringBuffer;
	pushChildrenToBuff(stringBuffer);
	return stringBuffer.str();
}
