'use server';

import type { ProductFormData } from '@/types/product';
import { revalidatePath } from 'next/cache';

const API_BASE = process.env.API_BASE_URL ?? 'http://localhost:8080';

export async function createProductAction(data: ProductFormData): Promise<{ id: number }> {
  const res = await fetch(`${API_BASE}/api/products`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });

  if (!res.ok) {
    throw new Error('商品の登録に失敗しました');
  }

  const result = await res.json();
  revalidatePath('/products');
  return result;
}

export async function updateProductAction(
  id: number,
  data: Partial<ProductFormData>
): Promise<void> {
  const res = await fetch(`${API_BASE}/api/products/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });

  if (!res.ok) {
    throw new Error('商品の更新に失敗しました');
  }

  revalidatePath('/products');
}

export async function deleteProductAction(id: number): Promise<void> {
  const res = await fetch(`${API_BASE}/api/products/${id}`, {
    method: 'DELETE',
  });

  if (!res.ok) {
    throw new Error('商品の削除に失敗しました');
  }

  revalidatePath('/products');
}
