package idv.tfp10207.nowclearnnow0818.market;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;


public class Merch_DesFragment extends Fragment {
    private static final String TAG = "TAG_Merch_DesFragment";
    private static final int POPWINDOWS_SHOPCAR = 1;
    private static final int POPWINDOWS_BUY = 2;
    private static final String SHOPPINGCARLIST = "shoppingCarList";
    private static final String ORDERUPDATEMERCH = "orderUpdateMerch";
    private Activity activity;
    private ImageView iv_MerchDes1_05, iv_MerchDesInfoToolbarBack_05, iv_MerchDesToolbarShopcar_05;
    private TextView tv_MerchName_05, tv_MerchPrice_05, tv_MerchContent_05, tv_MerchBrand_05, tv_MerchNum_05; /*tv_MerchDes_05, tv_MerchSoldNum_05*/
    private ImageButton ib_Shopping_05, ib_Buy_05;
    //private Bundle bundle = getArguments();
    private ViewGroup mRootView;

    private Bundle bundleShoppingList = new Bundle();
    private ShoppingCarMerch shoppingCarMerch = new ShoppingCarMerch();
    private List<ShoppingCarMerch> shoppingCarMerchAll = new ArrayList<>();//Nick//

    private int shoppintListPoto;
    private String shoppintListSeller;
    private String shoppintListMerchName;
    private int shoppintListMerchPrice;
    //private int shoppintListMerchNumber;
    //private int addShoppingCarMerchNum = 0;
    private String shoppingListMemberID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = getActivity(); //??????Activity??????

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_merch_des, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleMerchIntroductionViews();
        handleImageButtonAddShoppingView();
        hdndleToolBarMerchDes();
        handleImageButtonBuyView();
    }

    private void findViews(View view) {
        iv_MerchDes1_05 = view.findViewById(R.id.iv_MerchDes1_05);
        tv_MerchName_05 = view.findViewById(R.id.tv_MerchName_05);
        tv_MerchPrice_05 = view.findViewById(R.id.tv_MerchPrice_05);
        //tv_MerchSoldNum_05 = view.findViewById(R.id.tv_MerchSoldNum_05);
        //tv_MerchDes_05 = view.findViewById(R.id.tv_MerchDes_05);
        tv_MerchNum_05 = view.findViewById(R.id.tv_MerchNum_05);
        tv_MerchBrand_05 = view.findViewById(R.id.tv_MerchBrand_05);
        tv_MerchContent_05 = view.findViewById(R.id.tv_MerchContent_05);
        ib_Shopping_05 = view.findViewById(R.id.ib_Shopping_05);
        ib_Buy_05 = view.findViewById(R.id.ib_Buy_05);
        iv_MerchDesInfoToolbarBack_05 = view.findViewById(R.id.iv_MerchDesInfoToolbarBack_05);

        iv_MerchDesToolbarShopcar_05 = view.findViewById(R.id.iv_MerchDesToolbarShopcar_05);

        mRootView = (ViewGroup) activity.getWindow().getDecorView();
    }

    private void handleMerchIntroductionViews() {

        Bundle bundle = getArguments();

        int merchNumber = bundle.getInt("merchNumber");
        //??????????????????
        List<OrderUpdateMerchNumber> orderUpdateMerchNumbers = orderUpdateMerchDesloadFile();

        iv_MerchDes1_05.setImageResource(bundle.getInt("merchPoto"));
        tv_MerchName_05.setText("???????????? : " + bundle.getString("merchName"));
        tv_MerchPrice_05.setText("???????????? : $ " + bundle.getInt("merchPrice"));


        if(orderUpdateMerchNumbers == null){
            tv_MerchNum_05.setText("???????????? : " + merchNumber);
        }
        else{
            for(int i = 0 ; i < orderUpdateMerchNumbers.size() ; i++){
                if(orderUpdateMerchNumbers.get(i).getDrawableID() == bundle.getInt("merchPoto")){
                    merchNumber -=  orderUpdateMerchNumbers.get(i).getBuyMerchNumber();
                }
            }
            tv_MerchNum_05.setText("???????????? : " + merchNumber);
        }


        //tv_MerchNum_05.setText("???????????? : " + bundle.getInt("merchNumber")); ??????



        tv_MerchBrand_05.setText("???????????? : " + bundle.getString("merchBrand"));
        tv_MerchContent_05.setText("???????????? : " + bundle.getString("merchContent"));

        shoppintListPoto = bundle.getInt("merchPoto");
        shoppintListSeller = bundle.getString("sellerName");
        shoppintListMerchName = bundle.getString("merchName");
        shoppintListMerchPrice = bundle.getInt("merchPrice");
        shoppingListMemberID = bundle.getString("memberID");


    }

    private void handleImageButtonAddShoppingView() { //???????????????

        ib_Shopping_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow popupWindow = new popupWindow(activity, 1);
                View view = LayoutInflater.from(activity).inflate(R.layout.popwindows_shoppint_car, null);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
        });
    }


    private void handleImageButtonBuyView() { //????????????

        ib_Buy_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow popupWindow = new popupWindow(activity, 2);
                View view = LayoutInflater.from(activity).inflate(R.layout.popwindows_buy, null);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
        });
    }


    public class popupWindow extends PopupWindow implements View.OnClickListener {
        View view;
        Button bt_popWindowsAddShop_05, bt_popWindowsBuy_05;
        TextView tv_popWindowsShopPrice_05, tv_popWindowsShopNum_05, tv_popWindowsShopMerNum_05, tv_popWindowsBuyPrice_05, tv_popWindowsBuyMerNum_05, tv_popWindowsBuyNum_05;
        ImageView iv_popWindowsShop_05, iv_popWindowsBuy_05, iv_popWindowsShopCancel_05, iv_popWindowsBuyCancel_05;
        EditText etn_popWindowsShop_05, etn_popWindowsBuy_05;


        public popupWindow(Context mContext, int popWindowsState) {

            Bundle bundle = getArguments();

            int merchNumber = bundle.getInt("merchNumber");

            //??????????????????
            List<OrderUpdateMerchNumber> orderUpdateMerchNumbers = orderUpdateMerchDesloadFile();

            if (popWindowsState == POPWINDOWS_SHOPCAR) {
                this.view = LayoutInflater.from(mContext).inflate(R.layout.popwindows_shoppint_car, null);
                bt_popWindowsAddShop_05 = view.findViewById(R.id.bt_popWindowsAddShop_05);
                tv_popWindowsShopPrice_05 = view.findViewById(R.id.tv_popWindowsShopPrice_05);
                tv_popWindowsShopNum_05 = view.findViewById(R.id.tv_popWindowsShopNum_05);
                tv_popWindowsShopMerNum_05 = view.findViewById(R.id.tv_popWindowsShopMerNum_05);
                iv_popWindowsShop_05 = view.findViewById(R.id.iv_popWindowsShop_05);
                iv_popWindowsShopCancel_05 = view.findViewById(R.id.iv_popWindowsShopCancel_05);
                etn_popWindowsShop_05 = view.findViewById(R.id.etn_popWindowsShop_05); //??????????????????????????????

                iv_popWindowsShop_05.setImageResource(bundle.getInt("merchPoto"));
                tv_popWindowsShopPrice_05.setText("???????????? : $ " + bundle.getInt("merchPrice"));

                if(orderUpdateMerchNumbers == null){
                    tv_popWindowsShopMerNum_05.setText("???????????? : " + merchNumber);
                }
                else{
                    for(int i = 0 ; i < orderUpdateMerchNumbers.size() ; i++){
                        if(orderUpdateMerchNumbers.get(i).getDrawableID() == bundle.getInt("merchPoto")){
                            merchNumber -=  orderUpdateMerchNumbers.get(i).getBuyMerchNumber();
                        }
                    }
                    tv_popWindowsShopMerNum_05.setText("???????????? : " + merchNumber);
                }

                //tv_popWindowsShopMerNum_05.setText("???????????? : " + bundle.getInt("merchNumber")); ??????

                tv_popWindowsShopNum_05.setText("???????????? : ");

            } else if (popWindowsState == POPWINDOWS_BUY) {
                this.view = LayoutInflater.from(mContext).inflate(R.layout.popwindows_buy, null);
                bt_popWindowsBuy_05 = view.findViewById(R.id.bt_popWindowsBuy_05);
                tv_popWindowsBuyPrice_05 = view.findViewById(R.id.tv_popWindowsBuyPrice_05);
                tv_popWindowsBuyMerNum_05 = view.findViewById(R.id.tv_popWindowsBuyMerNum_05);
                tv_popWindowsBuyNum_05 = view.findViewById(R.id.tv_popWindowsBuyNum_05);
                iv_popWindowsBuy_05 = view.findViewById(R.id.iv_popWindowsBuy_05);
                iv_popWindowsBuyCancel_05 = view.findViewById(R.id.iv_popWindowsBuyCancel_05);
                etn_popWindowsBuy_05 = view.findViewById(R.id.etn_popWindowsBuy_05); //???????????????????????????

                iv_popWindowsBuy_05.setImageResource(bundle.getInt("merchPoto"));
                tv_popWindowsBuyPrice_05.setText("???????????? : $ " + bundle.getInt("merchPrice"));

                if(orderUpdateMerchNumbers == null){
                    tv_popWindowsBuyMerNum_05.setText("???????????? : " + merchNumber);
                }
                else{
                    for(int i = 0 ; i < orderUpdateMerchNumbers.size() ; i++){
                        if(orderUpdateMerchNumbers.get(i).getDrawableID() == bundle.getInt("merchPoto")){
                            merchNumber -=  orderUpdateMerchNumbers.get(i).getBuyMerchNumber();
                        }
                    }
                    tv_popWindowsBuyMerNum_05.setText("???????????? : " + merchNumber);
                }

                //tv_popWindowsBuyMerNum_05.setText("???????????? : " + bundle.getInt("merchNumber")); ??????

                tv_popWindowsBuyNum_05.setText("???????????? : ");
            }

            this.setOutsideTouchable(true);
            this.view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int height = 0;

                    if (popWindowsState == POPWINDOWS_SHOPCAR) {
                        height = view.findViewById(R.id.cl_popWindowAddShop_05).getTop();
                    } else if (popWindowsState == POPWINDOWS_BUY) {
                        height = view.findViewById(R.id.cl_popWindowBuy_05).getTop();
                    }

                    int y = (int) event.getY();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (y < height) {
                            dismiss();
                        }
                    }
                    return true;
                }
            });

            this.setContentView(this.view);
            this.setHeight(ConstraintLayout.LayoutParams.MATCH_PARENT);
            this.setWidth(ConstraintLayout.LayoutParams.MATCH_PARENT);
            this.setFocusable(true);
            ColorDrawable dw = new ColorDrawable(0xb0000000);
            this.setBackgroundDrawable(dw);

            //???????????????????????????

            if (popWindowsState == POPWINDOWS_SHOPCAR) {
                bt_popWindowsAddShop_05.setOnClickListener(this);
                iv_popWindowsShopCancel_05.setOnClickListener(this);
            } else if (popWindowsState == POPWINDOWS_BUY) {
                bt_popWindowsBuy_05.setOnClickListener(this);
                iv_popWindowsBuyCancel_05.setOnClickListener(this);


            }

        }

        @Override
        public void onClick(View v) {
            int id = v.getId();

            //??????
            List<ShoppingCarMerch> shoppingCarMerchLoad = loadShoppingCarMerchAllFile();


            switch (id) {
                case R.id.bt_popWindowsAddShop_05:

                    if("".equals(etn_popWindowsShop_05.getText().toString().trim())) {
                        break;
                    }

                        playAnim(v);

                    /*bundleShoppingList.putInt("shoppingListPoto", shoppintListPoto);
                    bundleShoppingList.putString("shoppingListSeller", shoppintListSeller);
                    bundleShoppingList.putString("shoppingListMerchName", shoppintListMerchName);
                    bundleShoppingList.putInt("shoppintListMerchPrice", shoppintListMerchPrice);
                    bundleShoppingList.putInt("shoppingListMerchNumber", Integer.parseInt(String.valueOf(etn_popWindowsShop_05.getText())));*/


                        if (shoppingCarMerchLoad == null || shoppingCarMerchLoad.size() == 0) {
                            shoppingCarMerchLoad = new ArrayList<>();
                            shoppingCarMerch.setDrawableID(shoppintListPoto);
                            shoppingCarMerch.setSeller(shoppintListSeller);
                            shoppingCarMerch.setMerchName(shoppintListMerchName);
                            shoppingCarMerch.setMerchPrice(shoppintListMerchPrice);
                            shoppingCarMerch.setMerchNumber(Integer.parseInt(String.valueOf(etn_popWindowsShop_05.getText())));
                            shoppingCarMerch.setMemberId(shoppingListMemberID);

                            shoppingCarMerch.setFirstMerchItem(true);
                            //addShoppingCarMerchNum = shoppingCarMerch.getMerchNumber();

                            shoppingCarMerchLoad.add(shoppingCarMerch);

                            //??????
                            saveShoppingCarMerchAllFile(shoppingCarMerchLoad);

                        } else {
                            shoppingCarMerch.setDrawableID(shoppintListPoto);
                            shoppingCarMerch.setSeller(shoppintListSeller);
                            shoppingCarMerch.setMerchName(shoppintListMerchName);
                            shoppingCarMerch.setMerchPrice(shoppintListMerchPrice);
                            shoppingCarMerch.setMerchNumber(Integer.parseInt(String.valueOf(etn_popWindowsShop_05.getText())));
                            shoppingCarMerch.setMemberId(shoppingListMemberID);

                            if (shoppingCarMerchLoad.get(shoppingCarMerchLoad.size() - 1).getMemberId() != shoppingListMemberID) {
                                shoppingCarMerch.setFirstMerchItem(true);
                            }


                            shoppingCarMerchLoad.add(shoppingCarMerchLoad.size(), shoppingCarMerch);

                            //TODO ?????????????????????????????????????????? ??????????????????????????????????????? ?????????????????????????????????????????????????????????
                            for (int j = 0; j < shoppingCarMerchLoad.size(); j++) {
                                //ShoppingCarMerch scm = shoppingCarMerchLoad.get(i);
                                for (int k = j + 1; k < shoppingCarMerchLoad.size(); k++) {
                                    //?????????????????????????????????   ??????????????????????????????
                                    if (shoppingCarMerchLoad.get(j).getMemberId().equals(shoppingCarMerchLoad.get(k).getMemberId()) && shoppingCarMerchLoad.get(j).getMerchName().equals(shoppingCarMerchLoad.get(k).getMerchName())) {
                                        int merchNumber = 0;
                                        merchNumber = shoppingCarMerchLoad.get(j).getMerchNumber() + shoppingCarMerchLoad.get(k).getMerchNumber();
                                        shoppingCarMerchLoad.get(j).setMerchNumber(merchNumber);
                                        shoppingCarMerchLoad.remove(k);
                                    } else if (shoppingCarMerchLoad.get(j).getMemberId().equals(shoppingCarMerchLoad.get(k).getMemberId())) {
                                        shoppingCarMerchLoad.get(k).setFirstMerchItem(false);
                                    }
//                                else if ( shoppingCarMerchLoad.get(j).getMemberId().equals(shoppingCarMerchLoad.get(k).getMemberId())){
//                                    String sameSellMerch = shoppingCarMerchLoad.get(k).getSeller();
//                                    shoppingCarMerchLoad.get(k).setSeller("$" + sameSellMerch);
//
//                                }
                                }
                            }
                            //????????????
                            Collections.sort(shoppingCarMerchLoad, new Comparator<ShoppingCarMerch>() {
                                @Override
                                public int compare(ShoppingCarMerch o1, ShoppingCarMerch o2) {
                                    return o1.getMemberId().compareTo(o2.getMemberId());
                                }
                            });


                            //??????
                            saveShoppingCarMerchAllFile(shoppingCarMerchLoad);
                        }


                        bundleShoppingList.putSerializable("shoppingCarMerch", (Serializable) shoppingCarMerchLoad);//(Serializable) shoppingCarMerchAll);//Nick// //shoppingCarMerch

                        dismiss();
                    break;
                case R.id.iv_popWindowsShopCancel_05: //??????????????? ????????????
                    dismiss();
                    break;
                case R.id.iv_popWindowsBuyCancel_05: //??????????????? ????????????
                    dismiss();
                    break;
                case R.id.bt_popWindowsBuy_05:
                    //TODO ??????????????????????????????
                    /*bundleShoppingList.putInt("shoppingListPoto", shoppintListPoto);
                    bundleShoppingList.putString("shoppingListSeller", shoppintListSeller);
                    bundleShoppingList.putString("shoppingListMerchName", shoppintListMerchName);
                    bundleShoppingList.putInt("shoppintListMerchPrice", shoppintListMerchPrice);
                    bundleShoppingList.putInt("shoppingListMerchNumber", Integer.parseInt(String.valueOf(etn_popWindowsBuy_05.getText())));*/

                    if("".equals(etn_popWindowsBuy_05.getText().toString().trim())) {
                        break;
                    }

                    if (shoppingCarMerchLoad == null || shoppingCarMerchLoad.size() == 0) {
                        shoppingCarMerchLoad = new ArrayList<>();

                        shoppingCarMerch.setDrawableID(shoppintListPoto);
                        shoppingCarMerch.setSeller(shoppintListSeller);
                        shoppingCarMerch.setMerchName(shoppintListMerchName);
                        shoppingCarMerch.setMerchPrice(shoppintListMerchPrice);
                        shoppingCarMerch.setMerchNumber(Integer.parseInt(String.valueOf(etn_popWindowsBuy_05.getText())));
                        //addShoppingCarMerchNum = addShoppingCarMerchNum + (Integer.parseInt(String.valueOf(etn_popWindowsBuy_05.getText())));
                        //shoppingCarMerch.setMerchNumber(addShoppingCarMerchNum); //+ (Integer.parseInt(String.valueOf(etn_popWindowsShop_05.getText()))));
                        shoppingCarMerch.setMemberId(shoppingListMemberID);

                        shoppingCarMerch.setFirstMerchItem(true);

                        shoppingCarMerchLoad.add(shoppingCarMerch);

                        //??????
                        saveShoppingCarMerchAllFile(shoppingCarMerchLoad);
                    } else {

                        shoppingCarMerch.setDrawableID(shoppintListPoto);
                        shoppingCarMerch.setSeller(shoppintListSeller);
                        shoppingCarMerch.setMerchName(shoppintListMerchName);
                        shoppingCarMerch.setMerchPrice(shoppintListMerchPrice);
                        shoppingCarMerch.setMerchNumber(Integer.parseInt(String.valueOf(etn_popWindowsBuy_05.getText())));
                        //addShoppingCarMerchNum = addShoppingCarMerchNum + (Integer.parseInt(String.valueOf(etn_popWindowsBuy_05.getText())));
                        //shoppingCarMerch.setMerchNumber(addShoppingCarMerchNum);
                        shoppingCarMerch.setMemberId(shoppingListMemberID);

                        if (shoppingCarMerchLoad.get(shoppingCarMerchLoad.size() - 1).getMemberId() != shoppingListMemberID) {
                            shoppingCarMerch.setFirstMerchItem(true);
                        }

                        shoppingCarMerchLoad.add(shoppingCarMerchLoad.size(), shoppingCarMerch);

                        //TODO ??????????????????????????????????????????   ??????????????????????????????
                        for (int j = 0; j < shoppingCarMerchLoad.size(); j++) {
                            //ShoppingCarMerch scm = shoppingCarMerchLoad.get(i);
                            for (int k = j + 1; k < shoppingCarMerchLoad.size(); k++) {
                                //?????????????????????????????????   ??????????????????????????????
                                if (shoppingCarMerchLoad.get(j).getMemberId().equals(shoppingCarMerchLoad.get(k).getMemberId()) && shoppingCarMerchLoad.get(j).getMerchName().equals(shoppingCarMerchLoad.get(k).getMerchName())) {
                                    int merchNumber = 0;
                                    merchNumber = shoppingCarMerchLoad.get(j).getMerchNumber() + shoppingCarMerchLoad.get(k).getMerchNumber();
                                    shoppingCarMerchLoad.get(j).setMerchNumber(merchNumber);
                                    shoppingCarMerchLoad.remove(k);
                                } else if (shoppingCarMerchLoad.get(j).getMemberId().equals(shoppingCarMerchLoad.get(k).getMemberId())) {
                                    shoppingCarMerchLoad.get(k).setFirstMerchItem(false);
                                }
//                                else if ( shoppingCarMerchLoad.get(j).getMemberId().equals(shoppingCarMerchLoad.get(k).getMemberId())){
//                                    shoppingCarMerchLoad.get(k).setSeller("");
//                                }
                            }
                        }

                        //????????????
                        Collections.sort(shoppingCarMerchLoad, new Comparator<ShoppingCarMerch>() {
                            @Override
                            public int compare(ShoppingCarMerch o1, ShoppingCarMerch o2) {
                                return o1.getMemberId().compareTo(o2.getMemberId());
                            }
                        });

                        //??????
                        saveShoppingCarMerchAllFile(shoppingCarMerchLoad);
                    }

                    bundleShoppingList.putSerializable("shoppingCarMerch", (Serializable) shoppingCarMerchLoad);

                    dismiss();

                    NavController navController = Navigation.findNavController(iv_MerchDes1_05);
                    navController.navigate(R.id.action_merch_DesFragment_to_shoppingListFragment, bundleShoppingList);


                    break;

            }

        }
    }

    private void hdndleToolBarMerchDes() {

        iv_MerchDesInfoToolbarBack_05.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);//??????
            navController.popBackStack();
        });


        iv_MerchDesToolbarShopcar_05.setOnClickListener(view -> { //?????????????????????

            //??????
            List<ShoppingCarMerch> shoppingCarMerchLoad = loadShoppingCarMerchAllFile();

            bundleShoppingList.putSerializable("shoppingCarMerch", (Serializable) shoppingCarMerchLoad);

            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_merch_DesFragment_to_shoppingListFragment, bundleShoppingList);

        });


    }

    // ???????????????????????????
    private void playAnim(final View view) {

        //??????int????????????????????????????????????????????????
        int[] startPosition = new int[2];
        int[] endPosition = new int[2];

        view.getLocationInWindow(startPosition);
        iv_MerchDesToolbarShopcar_05.getLocationInWindow(endPosition);

        PointF startF = new PointF();        //????????? startF
        PointF endF = new PointF();          //?????? endF
        PointF controlF = new PointF();      //????????? controlF

        startF.x = startPosition[0];
        startF.y = startPosition[1];
        endF.x = endPosition[0] + iv_MerchDesToolbarShopcar_05.getWidth() / 2 - view.getWidth() / 2;             //??????????????????????????????????????? ?????? ??????????????????????????????????????????
        endF.y = endPosition[1] + iv_MerchDesToolbarShopcar_05.getHeight() / 2 - view.getHeight() / 2;
        controlF.x = endF.x;
        controlF.y = startF.y;

        // ????????????????????? ?????? ??????
        final ImageView imageView = new ImageView(activity);
        mRootView.addView(imageView);
        imageView.setImageResource(R.drawable.icon_shopping_05); //????????????
        imageView.getLayoutParams().width = view.getMeasuredWidth();
        imageView.getLayoutParams().height = view.getMeasuredHeight();

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CartEvaluator(controlF), startF, endF);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                imageView.setX(pointF.x);
                imageView.setY(pointF.y);
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // ??????????????????????????????????????? ?????? ???????????????
                mRootView.removeView(imageView);

                // ???????????????????????????
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator animatorX = ObjectAnimator.ofFloat(iv_MerchDesToolbarShopcar_05, "scaleX", 1f, 1.2f, 1f);
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(iv_MerchDesToolbarShopcar_05, "scaleY", 1f, 1.2f, 1f);
                animatorSet.play(animatorX).with(animatorY);
                animatorSet.setDuration(400);
                animatorSet.start();
            }
        });

        valueAnimator.setDuration(800);
        valueAnimator.start();
    }

    public class CartEvaluator implements TypeEvaluator<PointF> {

        private PointF pointCur;
        private PointF mControlPoint;

        public CartEvaluator(PointF mControlPoint) {

            this.mControlPoint = mControlPoint;
            pointCur = new PointF();
        }

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            // ?????????????????????????????????????????????????????????
            pointCur.x = (1 - fraction) * (1 - fraction) * startValue.x
                    + 2 * fraction * (1 - fraction) * mControlPoint.x + fraction * fraction * endValue.x;
            pointCur.y = (1 - fraction) * (1 - fraction) * startValue.y
                    + 2 * fraction * (1 - fraction) * mControlPoint.y + fraction * fraction * endValue.y;

            return pointCur;
        }
    }


    /**
     * ??????
     */
    private void saveShoppingCarMerchAllFile(final List<ShoppingCarMerch> shoppingCarMerch) {
        try (
                // ??????FileOutputStream??????
                FileOutputStream fos = activity.openFileOutput(SHOPPINGCARLIST, Context.MODE_PRIVATE);
                // Java I/O????????????
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(shoppingCarMerch);
            oos.flush();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * ??????
     */

    public List<ShoppingCarMerch> loadShoppingCarMerchAllFile() {
        try (
                // ??????FileInputStream??????
                FileInputStream fis = activity.openFileInput(SHOPPINGCARLIST);
                // Java I/O????????????
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (List<ShoppingCarMerch>) ois.readObject();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }


    /**
     * ????????????????????????
     */

    public List<OrderUpdateMerchNumber> orderUpdateMerchDesloadFile() {
        try (
                // ??????FileInputStream??????
                FileInputStream fis = activity.openFileInput(ORDERUPDATEMERCH);
                // Java I/O????????????
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (List<OrderUpdateMerchNumber>) ois.readObject();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }

    /**
     * ????????????????????????
     */
    private void orderUpdateMerchDesSaveFile(final List<OrderUpdateMerchNumber> orderUpdateMerchNumbers) {
        try (
                // ??????FileOutputStream??????
                FileOutputStream fos = activity.openFileOutput(ORDERUPDATEMERCH, Context.MODE_PRIVATE);
                // Java I/O????????????
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(orderUpdateMerchNumbers);
            oos.flush();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


}