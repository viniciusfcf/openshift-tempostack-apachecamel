curl -X POST http://localhost:52853/ordens \
  -H "Content-Type: application/json" \
  -d '{
    "ItemName": "Laptop",
    "Quantity": 2,
    "UnitPrice": 1599.99,
    "CustomerName": "Alice Smith",
    "OrderDate": "2025-07-16"
  }'
