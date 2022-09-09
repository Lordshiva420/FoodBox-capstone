export class addProduct {
    public pId!: number;
    productName!: string;
    productDescription!: string;
    imageUrl!: string;
    unitPrice!: number;
    quantity!: number;
    active!: boolean;
    category:cate={cId:0,categoryName:""}

}
  export class cate{
    cId!: number;
    categoryName!: string;
  }