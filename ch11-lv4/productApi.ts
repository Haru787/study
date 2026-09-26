import type { Product, ProductSearchResult } from '@/types/product';

const API_BASE = process.env.API_BASE_URL ?? 'http://localhost:8080';

export async function fetchProduct(id: number): Promise<Product> {
  const res = await fetch(`${API_BASE}/api/products/${id}`, { cache: 'no-store' });
  if (!res.ok) throw new Error(`商品の取得に失敗しました (id: ${id})`);
  return res.json();
}

export async function searchProducts(params: {
  keyword?: string;
  category?: string;
  page?: number;
  perPage?: number;
}): Promise<ProductSearchResult> {
  const query = new URLSearchParams();
  if (params.keyword) query.set('keyword', params.keyword);
  if (params.category) query.set('category', params.category);
  if (params.page) query.set('page', String(params.page));
  if (params.perPage) query.set('perPage', String(params.perPage));

  const res = await fetch(`${API_BASE}/api/products/search?${query}`, {
    cache: 'no-store',
  });
  if (!res.ok) throw new Error('商品の検索に失敗しました');
  return res.json();
}

export async function deleteProduct(id: number): Promise<void> {
  const res = await fetch(`${API_BASE}/api/products/${id}`, { method: 'DELETE' });
  if (!res.ok) throw new Error('商品の削除に失敗しました');
}
