module Alife.MathUtil 
       ( 
         dotp,
         (+.),
         (-.),
         fixconv,
         solve
       ) where

-- Dot Product
dotp :: (Num a) => [a] -> [a] -> a
dotp r s = sum $ zipWith (*) r s

-- Vector Sum
(+.) :: (Num a) => [a] -> [a] -> [a]
(+.) [] [] = []
(+.) (ha:ta) (hb:tb) = (ha + hb):(ta +. tb) 
(+.) _ _ = error "Not equal length"

-- Vector Subtract
(-.) :: (Num a) => [a] -> [a] -> [a]
(-.) [] [] = []
(-.) (ha:ta) (hb:tb) = (ha - hb):(ta -. tb) 
(-.) _ _ = error "Not equal length"

-- Fixed Length Convolution
fixconv :: (Num a) => [a] -> [a] -> [a]
fixconv r s = [f d r s | d <- [to,to-1..from]  ] 
  where f d r s = dotp (reverse $ delayfix d $ wfill size r) s 
        size = length s
        to = div (size-1) 2
        from = 0 - div (size-1) 2

-- Wrap With Fillers Padding
wfill :: (Num a) => Int -> [a] -> [a] 
wfill size r 
  | length r < size = 
      let border = div (size - length r) 2
      in (replicate border 0) ++ r ++ (replicate border 0)
  | otherwise = 
      let border = div (length r - size) 2 
      in take size $ drop border r   

-- Delay With Fixed Length
delayfix :: (Num a) => Int -> [a] -> [a]
delayfix d r 
  | d >= 0 = take (length r) $ (replicate d 0) ++ r
  | otherwise = drop (-d) $  r ++ (replicate (-d) 0) 

-- 
solve :: (RealFrac a) => ([a] -> [a]) -> [a] -> a -> [[a]]
solve delta state h =  
  let next = rk4 delta h where
        rk4 d h y =
            let
                (.*) n = fmap (*n)
                shf = ((1/2).*)
                k0 = delta y 
                k1 = h .* (y +. shf k0)
                k2 = h .* (y +. shf k1)
                k3 = h .* (y +. k2)
            in
              y +. ((h/6) .* (k0 +. (2 .* k1) +. (2 .* k2) +. k3))
    in
      iterate next state

 