#!/bin/bash

# Set API URL and headers
API_URL="http://localhost:8080/drug"
HEADERS="Content-Type: application/json"

# Mock data
DRUG_1=$(cat <<EOF
{
  "id": 1,
  "name": "Ibuprofen",
  "commonName": "Advil",
  "activeSubstance": "Ibuprofen",
  "marketingAuthorizationHolder": "Johnson & Johnson",
  "pharmaceuticalForm": "Tablet",
  "maNumber": "MA-12345",
  "atcCode": "M01AE01",
  "strength": "200mg"
}
EOF
)

DRUG_2=$(cat <<EOF
{
  "name": "Paracetamol",
  "commonName": "Tylenol",
  "activeSubstance": "Acetaminophen",
  "marketingAuthorizationHolder": "McNeil Consumer Healthcare",
  "pharmaceuticalForm": "Capsule",
  "maNumber": "MA-67890",
  "atcCode": "N02BE01",
  "strength": "500mg"
}
EOF
)

# Test functions
test_add() {
    local response=$(curl -s -X POST "$API_URL/add" \
        -H "$HEADERS" \
        -d "$DRUG_1")
    echo "Add drug:"
    echo "$response"
    echo
}

test_add_drug2() {
    local response=$(curl -s -X POST "$API_URL/add" \
        -H "$HEADERS" \
        -d "$DRUG_2")
    echo "Add drug 2:"
    echo "$response"
    echo
}

test_get_by_name() {
    local response=$(curl -s -X GET "$API_URL/get/name/Ibuprofen" \
        -H "$HEADERS")
    echo "Get drug by name:"
    echo "$response"
    echo
}

test_get_by_name_drug2() {
    local response=$(curl -s -X GET "$API_URL/get/name/Paracetamol" \
        -H "$HEADERS")
    echo "Get drug 2 by name:"
    echo "$response"
    echo
}

test_get_by_id() {
    local response=$(curl -s -X GET "$API_URL/get/id/1" \
        -H "$HEADERS")
    echo "Get drug by ID:"
    echo "$response"
    echo
}

test_update() {
    local updated_drug=$(echo "$DRUG_1" | jq '.name = "Ibuprofen Updated"')
    local response=$(curl -s -X PUT "$API_URL/update" \
        -H "$HEADERS" \
        -d "$updated_drug")
    echo "Update drug:"
    echo "$response"
    echo
}

test_remove_by_name() {
    local response=$(curl -s -X DELETE "$API_URL/remove/name/Paracetamol" \
        -H "$HEADERS")
    echo "Remove drug by name:"
    echo "$response"
    echo
}

test_remove_by_id() {
    local response=$(curl -s -X DELETE "$API_URL/remove/id/1" \
        -H "$HEADERS")
    echo "Remove drug by ID:"
    echo "$response"
    echo
}

# Main execution
echo "Testing drug endpoints..."
test_add
test_add_drug2
test_get_by_name
test_get_by_name_drug2
test_get_by_id
test_update
test_remove_by_name
test_remove_by_id

echo "All tests completed."
