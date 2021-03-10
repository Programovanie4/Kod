module Main where

import qualified Delitele as F
import Test.HUnit
import System.Random

main = do
  g <- getStdGen
  runTestTT $ 
    TestList [ 
      TestList [ -- prva fcia
        let lst = map (+1) $ map (`mod` 20) $ map (abs) (randoms g :: [Int]) in
          TestList[
            TestCase $ assertEqual ("nesudelitelne " ++ (show x) ++ " " ++ (show y)) 
                                   (nesudelitelne x y) 
                                   (F.nesudelitelne x y) | (x,y) <- take 20 (zip lst (tail lst))]
            ,
        let lst = map (+1) $ map (`mod` 20) $ map (abs) (randoms g :: [Int]) in
          TestList[
            TestCase $ assertEqual ("prvocislo " ++ (show x)) 
                                   (prvocislo x) 
                                   (F.prvocislo x) | x <- take 20 lst]
            ,
        let lst = map (+1) $ map (`mod` 20) $ map (abs) (randoms g :: [Int]) in
          TestList[
            TestCase $ assertEqual ("delitele " ++ (show x)) 
                                   (delitele x) 
                                   (F.delitele x) | x <- take 20 lst]
        ,                           
        let lst = map (+1) $ map (`mod` 20) $ map (abs) (randoms g :: [Int]) in
          TestList[
            TestCase $ assertEqual ("disjunktne " ++ (show [1..x]) ++ (show [(x+1)..(2*x+1)]) ) 
                                   (disjunktne [1..x] [(x+1)..(2*x+1)]) 
                                   (F.disjunktne [1..x] [(x+1)..(2*x+1)]) | x <- take 20 lst]
        ,                           
        let lst = map (+1) $ map (`mod` 20) $ map (abs) (randoms g :: [Int]) in
          TestList[
            TestCase $ assertEqual ("disjunktne " ++ (show [1..x]) ++ (show [(x)..(2*x+1)]) ) 
                                   (disjunktne [1..x] [(x)..(2*x+1)]) 
                                   (F.disjunktne [1..x] [(x)..(2*x+1)]) | x <- take 20 lst]
            
       ]
     ]

-- riesenie tutora

-- fakt strasne napisane....
nesudelitelne :: Int -> Int -> Bool
nesudelitelne x y = disjunktne (delitele' x) (delitele' y)

disjunktne  :: [Int] -> [Int] -> Bool
disjunktne xs [] = True
disjunktne [] ys = True
disjunktne xs'@(x:xs) ys'@(y:ys)
        | x < y   = disjunktne xs ys'
        | x == y  = False
        | x > y   = disjunktne xs' ys

prvocislo   :: Int -> Bool
prvocislo x  = x > 1 && delitele x == []

delitele  :: Int -> [Int]
delitele x = [d | d <- [2..x-1], x `mod` d == 0]

delitele'  :: Int -> [Int]
delitele' x = [d | d <- [2..x], x `mod` d == 0]
