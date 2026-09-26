const API_BASE = process.env.NEXT_PUBLIC_API_BASE_URL ?? 'http://localhost:8080';

export async function createProduct(data: {
  name: string;
  price: number;
  stock: number;
  category: string;
  description: string;
  imageUrl: string;
}): Promise<{ id: number }> {
  const res = await fetch(`${API_BASE}/api/products`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });
  if (!res.ok) {
    throw new Error('商品の登録に失敗しました');
  }
  return res.json();
}

export async function updateProduct(
  id: number,
  data: {
    name: string;
    price: number;
    stock: number;
    category: string;
    description: string;
    imageUrl: string;
  }
): Promise<void> {
  const res = await fetch(`${API_BASE}/api/products/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });
  if (!res.ok) {
    throw new Error('商品の更新に失敗しました');
  }
}

export async function fetchProduct(id: number): Promise<import('@/types/product').Product> {
  const res = await fetch(`${API_BASE}/api/products/${id}`, { cache: 'no-store' });
  if (!res.ok) {
    throw new Error('商品情報の取得に失敗しました');
  }
  return res.json();
}
