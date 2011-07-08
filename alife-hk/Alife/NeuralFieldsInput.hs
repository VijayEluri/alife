module Alife.NeuralFieldsInput 
       (
         vectorCode
       ) where

import Alife.RecurUtil

vcRound :: (RealFrac a) => (a, Int) -> a -> Int
vcRound (max, n) x 
  | x > max = n
  | x < (-max) = 1
  | otherwise = let n' = fromIntegral (n-1) / 2
                in round (n' / max  * x + n' + 1)    

vectorCode :: (RealFrac a) => (a, Int) -> a -> [a] 
vectorCode (max, n) x = [if x' == i then 1 else 0 | i <- [1..n] ]
                       where x' = vcRound (max, n) x 
                             
---- Anamorphism exercise ----

pvc :: (Int, Int) -> Bool
pvc (_, 0) = True
pvc _ = False

gvc :: (RealFrac a) => (Int, Int) -> (a, (Int, Int))
gvc (x, y)
  | x==y = (1, (x, y-1)) 
  | otherwise = (0, (x, y-1))

vectorCode' :: (RealFrac a) => Int -> a -> a -> [a] 
vectorCode' n max x = reverse $ ana gvc pvc (vcRound (max, n) x, n) 

-----------------------------
