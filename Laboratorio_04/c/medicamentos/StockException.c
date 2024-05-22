#include "StockException.h"
#include <stdio.h>
#include <stdlib.h>

void handle_stock_exception(const char* msg) {
    fprintf(stderr, "Stock Exception: %s\n", msg);
    exit(EXIT_FAILURE);
}
