#include"CNode.h"
#include<string>

template<typename T>
CNode<T>::CNode()
{
	childrenCount = 0;
}
template<typename T>
CNode<T>::CNode(const T& value, const int whatAmI)
{
	this->value = value;
	this->whatAmI = whatAmI;
	childrenCount = 0;
}

template<typename T>
CNode<T>::~CNode()
{
	for (int i = 0; i < children.size(); i++)
	{
		delete children[i];
	}
}

template<typename T>
CNode<T>::CNode(const CNode<T>& node)
{

	for (int i = 0; i < node.children.size(); i++)
	{
		children.push_back(new CNode((*node.children[i])));
	}
	childrenCount = node.childrenCount;
	value = node.value;
	whatAmI = node.whatAmI;
}

template<typename T>
void CNode<T>::incrementChildrenCount()
{
	childrenCount++;
}

template<typename T>
void CNode<T>::pushChld(CNode* child)
{
	children.push_back(child);
}
 
template<typename T>
CNode<T>* CNode<T>::getChild(int index) const	
{
	return children[index];
}

template<typename T>
int CNode<T>::getChildrenCount() const
{
	return childrenCount;
}

template<typename T>
void CNode<T>::setChild(const int index,const CNode<T>& child)
{
	children[index] = new CNode(child);
}

template<typename T>
CNode<T>& CNode<T>::operator=(const CNode<T>& node)
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

template<typename T>
int CNode<T>::getWhatAmI() const
{
	return whatAmI;
}

template<typename T>
T CNode<T>::getValue() const
{
	return value;
}

template<typename T>
void CNode<T>::printChild(std::stringstream& stringBuffer)
{
	stringBuffer << value << " ";
	int counter = 0;
	while (counter < childrenCount)
	{
		(*children[counter]).printChild(stringBuffer);
		counter++;
	}
}

template<typename T>
CNode<T>* CNode<T>::getNodeBefore() const
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
