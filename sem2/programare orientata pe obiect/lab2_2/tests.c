#include <stdio.h>
#include <string.h>
#include <assert.h>
#include "tests.h"

void test_all() {
	test_oferta();
	test_agentie();
	test_service();
	printf("Testele au trecut!\n");
}

void test_oferta() {
	oferta off1 = init_oferta("teren", 2000, "E123", 100000);
	oferta off2 = init_oferta("casa", 1000, "E124", 50000);
	oferta off3 = init_oferta("apartament", 300, "E125", 20000);
	oferta off4 = init_oferta("teren", 2000, "E128", -5);
	oferta off5 = init_oferta("hotel", 500, "E127", 200000);
	oferta off6 = init_oferta("casa", -1000, "E126", 50000);
	oferta off7 = init_oferta("casa", 1500, "", 40000);
	
	assert(validate_oferta(off1) == 0);
	assert(validate_oferta(off2) == 0);
	assert(validate_oferta(off3) == 0);
	assert(validate_oferta(off4) == -1);
	assert(validate_oferta(off5) == -2);
	assert(validate_oferta(off6) == -3);
	assert(validate_oferta(off7) == -4);

	assert(strcmp(get_tip(off2), "casa") == 0);
	assert(get_suprafata(off2) == 1000);
	assert(strcmp(get_adresa(off2), "E124") == 0);
	assert(get_pret(off2) == 50000);

	set_oferta(off2, off1);
	assert(strcmp(get_tip(off2), "teren") == 0);
	assert(get_suprafata(off2) == 2000);
	assert(strcmp(get_adresa(off2), "E123") == 0);
	assert(get_pret(off2) == 100000);

	assert(cmp_oferta(off1, off2) == 1);
	assert(cmp_oferta(off1, off3) == 0);

	oferta aux = copyelem(off1);
	assert(cmp_oferta(off1, aux) == 1);

	destroy_oferta(off1);
	destroy_oferta(off2);
	destroy_oferta(off3);
	destroy_oferta(off4);
	destroy_oferta(off5);
	destroy_oferta(off6);
	destroy_oferta(aux);
}

void test_agentie() {
	agentie ag = init_agentie();
	oferta off1 = init_oferta("teren", 2000, "E123", 100000);
	oferta off2 = init_oferta("casa", 1000, "E124", 50000);
	oferta off3 = init_oferta("apartament", 300, "E125", 20000);

	add_elem(ag, off1);
	add_elem(ag, off2);
	add_elem(ag, off3);

	assert(get_len(ag) == 3);
	assert(cmp_oferta(get_elem_at(ag, 1), off2) == 1);

	delete_elem_at(ag, 1);
	assert(get_len(ag) == 2);
	assert(cmp_oferta(get_elem_at(ag, 1), off3) == 1);

	assert(find_elem(ag, off3) == 1);
	assert(find_elem(ag, off2) == -1);

	update_elem(ag, off1, off2);
	assert(cmp_oferta(get_elem_at(ag,0), off2) == 1);


	destroy_oferta(off1);
	destroy_oferta(off2);
	destroy_oferta(off3);
	destroy_agentie(ag);
}

void test_service() {
	agentie ag = init_agentie();
	oferta off1 = init_oferta("teren", 2000, "E123", 100000);
	oferta off2 = init_oferta("casa", 1000, "E124", 50000);
	oferta off3 = init_oferta("apartament", 300, "E125", 20000);

	assert(AddElem(ag, "teren", 2000, "E123", 100000) == 0);
	assert(AddElem(ag, "casa", 1000, "E124", 50000) == 0);
	assert(AddElem(ag, "apartament", 300, "E125", 20000) == 0);
	assert(AddElem(ag, "hotel", 500, "E127", 200000) == -2);

	assert(get_len(ag) == 3);
	assert(cmp_oferta(get_elem_at(ag, 1), off2) == 1);

	assert(DelElem(ag, "casa", 1000, "E124", 50000) == 0);
	assert(get_len(ag) == 2);
	assert(cmp_oferta(get_elem_at(ag, 1), off3) == 1);
	assert(DelElem(ag, "hotel", 500, "E127", 200000) == -1);
	
	Update(ag, off1, off2);
	assert(cmp_oferta(get_elem_at(ag,0), off2) == 1);

	agentie filtred = getAllOfferts(ag, "casa");
	assert(get_len(filtred) == 1);

	assert(AddElem(ag, "casa", 1000, "E126", 55000) == 0);
	assert(get_len(ag) == 3);
	agentie filtred1 = getAllOfferts(ag, "casa");
	assert(get_len(filtred1) == 2);

	agentie l = init_agentie();
	AddElem(l, "teren", 2000, "E123", 100000);
	AddElem(l, "casa", 1000, "E124", 50000);
	AddElem(l, "apartament", 300, "E125", 20000);
	agentie sorted = sort_by_type(l);

	oferta of1 = get_elem_at(sorted, 0);
	assert(strcmp(of1->tip, "teren") == 0);
	sorted = sort_by_price(l);

	oferta of2 = get_elem_at(sorted, 0);
	assert(strcmp(of2->tip, "apartament") == 0);
	
	destroy_oferta(of1);
	destroy_oferta(of2);
	destroy_agentie(l);

	destroy_oferta(off1);
	destroy_oferta(off2);
	destroy_oferta(off3);
	destroy_agentie(ag);
}