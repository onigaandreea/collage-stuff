#include <iostream>
#include <fstream>

int main()
{
    bool identical = true;
    std::ifstream file1("output1.txt");
    std::ifstream file2("output2.txt");
    int byte1, byte2;
    while ((byte1 = file1.get()) != -1) {
        byte2 = file2.get();
        if (byte1 != byte2) {
            identical = false;
            break;
        }
    }

    if (identical) {
        std::cout << "Matricile sunt identice." << std::endl;
    }
    else {
        std::cout << "Matricile difera." << std::endl;
    }

    return 0;
}
