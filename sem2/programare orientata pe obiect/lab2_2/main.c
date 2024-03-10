#define _CRTDBG_MAP_ALLOC
#include <crtdbg.h>
#include "tests.h"
#include "user_interface.h"

int main(int argc, char** argv) {
	test_all();
	run();
	//_CrtDumpMemoryLeaks();
	return 0;
}
