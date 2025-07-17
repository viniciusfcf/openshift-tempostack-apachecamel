curl -X POST http://rest-kaoto-my-app.apps.cluster-z5h76.z5h76.sandbox2768.opentlc.com/ordens \
  -H "Content-Type: application/json" \
  -d '{
    "ItemName": "Laptop",
    "Quantity": 2,
    "UnitPrice": 1599.99,
    "CustomerName": "Alice Smith",
    "OrderDate": "2025-07-16"
  }'
