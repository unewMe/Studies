#pragma once
#include<string>

//inline std::string operator+(const std::string& a, const std::string& b)
//{
//    return "\"" + a.substr(1, a.size() - 2) + b.substr(1, b.size() - 2) + "\"";
//}


inline std::string operator-(const std::string& a, const std::string& b)
{
    std::string modifiedA = a.substr(1, a.size() - 2);
    std::string modifiedB = b.substr(1, b.size() - 2);

    std::size_t pos = modifiedA.rfind(modifiedB);
    if (pos != std::string::npos) {
        return "\"" + modifiedA.substr(0, pos) + modifiedA.substr(pos + modifiedB.size(), modifiedA.size()) + "\"";
    }
    return a;
}


inline std::string operator*(const std::string& a, const std::string& b)
{
    std::string modifiedA = a.substr(1, a.size() - 2);
    std::string modifiedB = b.substr(1, b.size() - 2);

    if (modifiedB.empty()) return a;

    std::string result = modifiedA;
    char firstChar = modifiedB[0];
    std::string toInsert = modifiedB.substr(1);

    size_t pos = 0;
    while ((pos = result.find(firstChar, pos)) != std::string::npos)
    {
        result.insert(pos + 1, toInsert);
        pos += modifiedB.length();
    }
    return "\"" + result + "\"";
}


inline std::string operator/(const std::string& a, const std::string& b)
{
    std::string modifiedA = a.substr(1, a.size() - 2);
    std::string modifiedB = b.substr(1, b.size() - 2);

    if (modifiedB.empty() || modifiedB.length() == 1) return a;

    std::string result = modifiedA;
    char firstChar = modifiedB[0];
    size_t pos = 0;

    while ((pos = result.find(modifiedB, pos)) != std::string::npos)
    {
        result.erase(pos + 1, modifiedB.length() - 1);
        pos += 1;
    }
    return "\"" + result + "\"";
}