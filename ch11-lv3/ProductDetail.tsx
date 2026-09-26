import type { Product } from '@/types/product';

type Props = {
  product: Product;
};

export default function ProductDetail({ product }: Props) {
  return (
    <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
      <div>
        <img
          src={product.imageUrl}
          alt={product.name}
          className="w-full rounded-xl shadow-md object-cover aspect-square"
        />
      </div>
      <div className="space-y-4">
        <h1 className="text-3xl font-bold text-gray-900">{product.name}</h1>
        <p className="text-sm text-gray-500">{product.category}</p>
        <p className="text-4xl font-extrabold text-indigo-600">
          ¥{product.price.toLocaleString()}
        </p>
        <p className="text-sm text-gray-500">在庫: {product.stock}点</p>
        <span
          className={`inline-block text-sm px-3 py-1 rounded-full font-medium ${
            product.isActive
              ? 'bg-green-100 text-green-700'
              : 'bg-gray-100 text-gray-500'
          }`}
        >
          {product.isActive ? '販売中' : '販売停止'}
        </span>
        <div className="border-t pt-4">
          <h2 className="text-base font-semibold text-gray-700 mb-1">商品説明</h2>
          <p className="text-gray-600 leading-relaxed">{product.description}</p>
        </div>
        <p className="text-xs text-gray-400">
          登録日: {new Date(product.createdAt).toLocaleDateString('ja-JP')}
        </p>
      </div>
    </div>
  );
}
