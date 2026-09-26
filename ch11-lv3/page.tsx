import type { Metadata } from 'next';
import { fetchProduct, fetchRelatedProducts } from '@/lib/productApi';
import ProductDetail from './components/ProductDetail';
import ProductActions from './components/ProductActions';
import ProductList from '../components/ProductList';

type Props = {
  params: { id: string };
};

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const product = await fetchProduct(Number(params.id));
  return {
    title: `${product.name} | ECサイト管理画面`,
  };
}

export default async function ProductDetailPage({ params }: Props) {
  const product = await fetchProduct(Number(params.id));

  const related = await fetchRelatedProducts(product.category);

  return (
    <main className="max-w-5xl mx-auto px-4 py-8">
      <nav className="text-sm text-gray-500 mb-6">
        <a href="/products" className="hover:underline">商品一覧</a>
        <span className="mx-2">/</span>
        <span>{product.name}</span>
      </nav>
      <ProductDetail product={product} />
      <ProductActions productId={product.id} isActive={product.isActive} />
      {related.length > 0 && (
        <section className="mt-12">
          <h2 className="text-xl font-bold text-gray-800 mb-4">関連商品</h2>
          <ProductList products={related} />
        </section>
      )}
    </main>
  );
}
