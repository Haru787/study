import type { Product } from '@/types/product';

const API_BASE = process.env.API_BASE_URL ?? 'http://localhost:8080';

export async function fetchProduct(id: number): Promise<Product> {
  const res = await fetch(`${API_BASE}/api/products/${id}`, {
    cache: 'no-store',
  });
  if (!res.ok) {
    throw new Error(`商品の取得に失敗しました (id: ${id})`);
  }
  return res.json();
}

export async function fetchRelatedProducts(category: string): Promise<Product[]> {
  const res = await fetch(
    `${API_BASE}/api/products?category=${encodeURIComponent(category)}&limit=4`,
    { next: { revalidate: 60 } }
  );
  if (!res.ok) {
    return [];
  }
  return res.json();
}
