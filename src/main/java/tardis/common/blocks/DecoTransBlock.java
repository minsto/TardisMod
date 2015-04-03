package tardis.common.blocks;

import net.minecraft.world.IBlockAccess;
import tardis.TardisMod;
import io.darkcraft.darkcore.mod.abstracts.AbstractBlock;

public class DecoTransBlock extends AbstractBlock
{
	private static final String[]	subs	= new String[] { "Glass" };

	public DecoTransBlock()
	{
		super(TardisMod.modName);
	}

	@Override
	public void initData()
	{
		setBlockName("DecoGlass");
		setSubNames(subs);
	}

	@Override
	public void initRecipes()
	{
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess w, int s, int x, int y, int z, int mX, int mY, int mZ)
	{
		if (w.getBlock(mX, mY, mZ) == this && w.getBlockMetadata(mX, mY, mZ) == w.getBlockMetadata(x, y, z))
			return false;
		return super.shouldSideBeRendered(w, s, x, y, z, mX, mY, mZ);
	}
}
