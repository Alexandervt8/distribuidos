#ifndef STOCK_H
#define STOCK_H

#include "Medicine.h"

extern Medicine stock[MAX_MEDICINES];
extern int medicine_count;

void initialize_stock();
void list_medicines(int sock);

#endif // STOCK_H
